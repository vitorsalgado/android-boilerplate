package com.example.android.utils.widgets;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClockTimePickerDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
	public static final String BUNDLE_TIME = "widgets.calendartimepicker.TIME";
	public static final String DATETIME_FORMAT = "widgets.calendartimepicker.DATETIME_FORMAT";
	public static final int REQUEST_CODE = 99;

	@FunctionalInterface
	public interface ClockTimePickerListener {
		void onTimeSelected(SelectedTime selectedTime);
	}

	@NonNull
	private ClockTimePickerListener clockTimePickerListener;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if (getArguments() == null) {
			throw new IllegalStateException("This fragment needs some arguments to correctly initialize");
		}

		final Calendar calendar = Calendar.getInstance();
		final String incomingTime = getArguments().getString(BUNDLE_TIME);
		final String commonDateFormat = getArguments().getString(DATETIME_FORMAT);

		if (commonDateFormat == null || commonDateFormat.isEmpty()) {
			throw new IllegalArgumentException("date format is required");
		}

		if (incomingTime != null && !incomingTime.isEmpty()) {
			try {
				DateFormat dateFormat = new SimpleDateFormat(commonDateFormat, Locale.getDefault());
				Date date = dateFormat.parse(incomingTime);

				calendar.setTime(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(), this, hour, minute, true);
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);

		try {
			if (getTargetFragment() == null) {
				clockTimePickerListener = (ClockTimePickerListener) context;
			} else {
				clockTimePickerListener = (ClockTimePickerListener) getTargetFragment();
			}
		} catch (@NonNull final ClassCastException e) {
			throw new ClassCastException(context.toString() + " or target fragment must implement OnDateSelectedListener");
		}
	}

	@Override
	public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);

		clockTimePickerListener.onTimeSelected(new ClockTimePickerDialog.SelectedTime(calendar.getTime()));
	}

	public static class SelectedTime {
		@NonNull
		private final Date date;

		SelectedTime(@NonNull Date date) {
			this.date = date;
		}

		@NonNull
		public Date getDate() {
			return date;
		}
	}

}
