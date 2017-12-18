package br.com.vitorsalgado.androidstarter.widgets;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarDatePickerDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	public static final String BUNDLE_DATE = "widgets.calendardatepicker.DATE";
	public static final String DATE_FORMAT = "widgets.calendardatepicker.DATE_FORMAT";
	public static final int REQUEST_CODE = 98;

	@FunctionalInterface
	public interface CalendarDatePickerListener {
		void onDateSelected(SelectedDate selectedDate);
	}

	@Nullable
	private CalendarDatePickerListener calendarDatePickerListener;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if (getArguments() == null) {
			throw new IllegalStateException("This fragment needs some arguments to correctly initialize");
		}

		final Calendar calendar = Calendar.getInstance();
		final String incomingDate = getArguments().getString(BUNDLE_DATE);
		final String commonDateFormat = getArguments().getString(DATE_FORMAT);

		if (commonDateFormat == null || commonDateFormat.isEmpty()) {
			throw new IllegalArgumentException("date format is required");
		}

		if (incomingDate != null && !incomingDate.isEmpty()) {
			try {
				DateFormat dateFormat = new SimpleDateFormat(commonDateFormat, Locale.getDefault());
				Date date = dateFormat.parse(incomingDate);

				calendar.setTime(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		final int year = calendar.get(Calendar.YEAR);
		final int month = calendar.get(Calendar.MONTH);
		final int day = calendar.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		try {
			if (getTargetFragment() == null) {
				calendarDatePickerListener = (CalendarDatePickerListener) context;
			} else {
				calendarDatePickerListener = (CalendarDatePickerListener) getTargetFragment();
			}
		} catch (@NonNull final ClassCastException e) {
			throw new ClassCastException(context.toString() + " or target fragment must implement OnDateSelectedListener");
		}
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);

		calendarDatePickerListener.onDateSelected(new SelectedDate(calendar.getTime()));
	}

	public static class SelectedDate {
		@NonNull
		private final Date date;

		SelectedDate(@NonNull Date date) {
			this.date = date;
		}

		@NonNull
		public Date getDate() {
			return date;
		}
	}
}
