package com.example.utils.widgets

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.TimePicker
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ClockTimePickerDialog : DialogFragment(), TimePickerDialog.OnTimeSetListener {
  companion object {
    const val BUNDLE_TIME = "com.example.android.utils.widgets.clocktimepicker.TIME"
    const val DATETIME_FORMAT = "com.example.android.utils.widgets.clocktimepicker.DATETIME_FORMAT"
    const val REQUEST_CODE = 99
  }

  interface ClockTimePickerListener {
    fun onTimeSelected(selectedTime: SelectedTime)
  }

  class SelectedTime internal constructor(val date: Date)

  private var clockTimePickerListener: ClockTimePickerListener? = null

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    if (arguments == null) {
      throw IllegalStateException("This fragment needs some arguments to correctly initialize")
    }

    val calendar = Calendar.getInstance()
    val incomingTime = arguments!!.getString(BUNDLE_TIME)
    val commonDateFormat = arguments!!.getString(DATETIME_FORMAT)

    if (commonDateFormat == null || commonDateFormat.isEmpty()) {
      throw IllegalArgumentException("date format is required")
    }

    if (incomingTime != null && !incomingTime.isEmpty()) {
      try {
        val dateFormat = SimpleDateFormat(commonDateFormat, Locale.getDefault())
        val date = dateFormat.parse(incomingTime)

        calendar.time = date
      } catch (e: ParseException) {
        throw RuntimeException(e)
      }

    }

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    return TimePickerDialog(activity, this, hour, minute, true)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)

    clockTimePickerListener = try {
      if (targetFragment == null) {
        context as ClockTimePickerListener
      } else {
        targetFragment as ClockTimePickerListener?
      }
    } catch (e: ClassCastException) {
      throw ClassCastException(context.toString() + " or target fragment must implement OnDateSelectedListener")
    }

  }

  override fun onTimeSet(timePicker: TimePicker, hourOfDay: Int, minute: Int) {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
    calendar.set(Calendar.MINUTE, minute)

    clockTimePickerListener!!.onTimeSelected(SelectedTime(calendar.time))
  }
}
