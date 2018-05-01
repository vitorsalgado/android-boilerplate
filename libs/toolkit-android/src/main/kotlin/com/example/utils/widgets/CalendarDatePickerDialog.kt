package com.example.utils.widgets

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CalendarDatePickerDialog : DialogFragment(), DatePickerDialog.OnDateSetListener {
  @FunctionalInterface
  interface CalendarDatePickerListener {
    fun onDateSelected(selectedDate: SelectedDate)
  }

  class SelectedDate internal constructor(val date: Date)

  companion object {
    const val BUNDLE_DATE = "com.example.android.utils.widgets.calendardatepicker.DATE"
    const val DATE_FORMAT = "com.example.android.utils.widgets.calendardatepicker.DATE_FORMAT"
    const val REQUEST_CODE = 98
  }

  private var calendarDatePickerListener: CalendarDatePickerListener? = null

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    if (arguments == null) {
      throw IllegalStateException("This fragment needs some arguments to correctly initialize")
    }

    val calendar = Calendar.getInstance()
    val incomingDate = arguments!!.getString(BUNDLE_DATE)
    val commonDateFormat = arguments!!.getString(DATE_FORMAT)

    if (commonDateFormat == null || commonDateFormat.isEmpty()) {
      throw IllegalArgumentException("date format is required")
    }

    if (incomingDate != null && !incomingDate.isEmpty()) {
      try {
        val dateFormat = SimpleDateFormat(commonDateFormat, Locale.getDefault())
        val date = dateFormat.parse(incomingDate)

        calendar.time = date
      } catch (e: ParseException) {
        throw RuntimeException(e)
      }

    }

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return DatePickerDialog(activity!!, this, year, month, day)
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)

    calendarDatePickerListener = try {
      if (targetFragment == null) {
        context as CalendarDatePickerListener
      } else {
        targetFragment as CalendarDatePickerListener?
      }
    } catch (e: ClassCastException) {
      throw ClassCastException(context.toString() + " or target fragment must implement OnDateSelectedListener")
    }

  }

  override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)

    calendarDatePickerListener!!.onDateSelected(SelectedDate(calendar.time))
  }
}
