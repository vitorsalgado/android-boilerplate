package com.example.utils.components.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.R;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickDlg extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	public static final String BUNDLE_DATE = "com.example.utils.fragments.DateDlg";
	public static final int REQUEST_CODE = 98;

	@NonNull
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar calendar = Calendar.getInstance();
		final String incomingDate = getArguments().getString(BUNDLE_DATE);

		if (incomingDate != null && !incomingDate.isEmpty()) {
			try {
				DateFormat dateFormat = new SimpleDateFormat(getString(R.string.common_date_format), Locale.getDefault());
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
	public void onDateSet(DatePicker view, int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);

		EventBus.getDefault().post(new SelectedDate(calendar.getTime()));
	}

	public static class SelectedDate {
		private Date date;

		public SelectedDate(@NonNull Date date) {
			this.date = date;
		}

		public Date getDate() {
			return date;
		}
	}
}
