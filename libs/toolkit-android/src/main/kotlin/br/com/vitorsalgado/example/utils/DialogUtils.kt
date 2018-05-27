package br.com.vitorsalgado.example.utils

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import br.com.vitorsalgado.example.utils.widgets.CalendarDatePickerDialog
import br.com.vitorsalgado.example.utils.widgets.ClockTimePickerDialog
import br.com.vitorsalgado.example.utils.widgets.TransparentProgressDialog

object DialogUtils {
  fun promptToDiscard(
    context: Context,
    onKeepClickListener: DialogInterface.OnClickListener,
    onDiscardClickListener: DialogInterface.OnClickListener
  ): AlertDialog {
    return prompt(
      context,
      R.string.common_prompt_discard_data,
      R.string.common_keep_editing,
      R.string.common_discard,
      onKeepClickListener,
      onDiscardClickListener)
  }

  fun prompt(
    context: Context,
    @StringRes message: Int,
    @StringRes okButtonLabel: Int,
    @StringRes cancelButtonLabel: Int,
    onOkClick: DialogInterface.OnClickListener,
    onCancelClick: DialogInterface.OnClickListener
  ): AlertDialog {
    return AlertDialog.Builder(context)
      .setMessage(message)
      .setPositiveButton(okButtonLabel, onOkClick)
      .setNegativeButton(cancelButtonLabel, onCancelClick)
      .create()
  }

  fun retry(
    context: Context,
    @StringRes message: Int,
    onOkClick: DialogInterface.OnClickListener,
    onCancelClick: DialogInterface.OnClickListener
  ): AlertDialog {
    return AlertDialog.Builder(context)
      .setMessage(message)
      .setPositiveButton(R.string.common_retry, onOkClick)
      .setNegativeButton(R.string.common_cancel, onCancelClick)
      .create()
  }

  fun singleChoice(context: Context, title: String, options: Array<String>, onClickListener: DialogInterface.OnClickListener): Dialog {
    return AlertDialog.Builder(context)
      .setTitle(title)
      .setItems(options, onClickListener)
      .create()
  }

  fun simpleOk(context: Context, @StringRes title: Int, @StringRes message: Int, onDismissListener: DialogInterface.OnDismissListener): Dialog {
    return simpleOk(context, context.getString(title), context.getString(message), onDismissListener)
  }

  fun simpleOk(context: Context, @StringRes title: Int, @StringRes message: Int): Dialog {
    return simpleOk(context, context.getString(title), context.getString(message), null)
  }

  fun simpleOk(context: Context, title: String, message: String): Dialog {
    return simpleOk(context, title, message, null)
  }

  private fun simpleOk(context: Context, title: String, message: String, onDismissListener: DialogInterface.OnDismissListener?): Dialog {
    return AlertDialog.Builder(context)
      .setTitle(title)
      .setMessage(message)
      .setNeutralButton(R.string.dialog_action_ok, null)
      .setOnDismissListener(onDismissListener)
      .create()
  }

  fun datePicker(fragmentManager: FragmentManager, currentDate: String) {
    val bundle = Bundle()
    bundle.putString(CalendarDatePickerDialog.BUNDLE_DATE, currentDate)

    val datePicker = CalendarDatePickerDialog()
    datePicker.arguments = bundle
    datePicker.show(fragmentManager, "datePicker")
  }

  fun datePicker(targetFragment: Fragment, fragmentManager: FragmentManager, currentDate: String) {
    val bundle = Bundle()
    bundle.putString(CalendarDatePickerDialog.BUNDLE_DATE, currentDate)

    val datePicker = CalendarDatePickerDialog()
    datePicker.setTargetFragment(targetFragment, CalendarDatePickerDialog.REQUEST_CODE)
    datePicker.arguments = bundle
    datePicker.show(fragmentManager, "datePicker")
  }

  fun timePicker(fragmentManager: FragmentManager, currentTime: String) {
    val bundle = Bundle()
    bundle.putString(ClockTimePickerDialog.BUNDLE_TIME, currentTime)

    val timePicker = ClockTimePickerDialog()
    timePicker.arguments = bundle
    timePicker.show(fragmentManager, "timePicker")
  }

  fun timePicker(targetFragment: Fragment, fragmentManager: FragmentManager, currentTime: String) {
    val bundle = Bundle()
    bundle.putString(ClockTimePickerDialog.BUNDLE_TIME, currentTime)

    val timePicker = ClockTimePickerDialog()
    timePicker.setTargetFragment(targetFragment, ClockTimePickerDialog.REQUEST_CODE)
    timePicker.arguments = bundle
    timePicker.show(fragmentManager, "timePicker")
  }

  fun loading(context: Context, @StyleRes style: Int, @ColorRes color: Int): Dialog {
    return TransparentProgressDialog(context, style, color)
  }

  fun dismiss(progressDialog: Dialog?) {
    if (progressDialog == null) {
      return
    }

    progressDialog.dismiss()
  }
}
