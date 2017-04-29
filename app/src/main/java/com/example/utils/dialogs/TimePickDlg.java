package com.example.utils.dialogs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.example.R;

public class TimePickDlg extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    public static final String BUNDLE_TIME = "com.example.utils.fragments.TimeDlg";
    public static final int REQUEST_CODE = 99;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        final Calendar calendar = Calendar.getInstance();
        final String incomingTime = getArguments().getString(BUNDLE_TIME);

        if (incomingTime != null && !incomingTime.isEmpty()) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(getString(R.string.common_time_format), Locale.getDefault());
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
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        EventBus.getDefault().post(new TimePickDlg.SelectedTime(calendar.getTime()));
    }

    public static class SelectedTime {
        private Date date;

        public SelectedTime(@NonNull Date date) {
            this.date = date;
        }

        public Date getDate() {
            return date;
        }
    }

}
