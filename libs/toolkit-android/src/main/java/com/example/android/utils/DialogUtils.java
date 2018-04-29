package com.example.android.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import com.example.android.R;
import com.example.android.utils.widgets.CalendarDatePickerDialog;
import com.example.android.utils.widgets.ClockTimePickerDialog;
import com.example.android.utils.widgets.TransparentProgressDialog;

public final class DialogUtils {
	public static AlertDialog promptToDiscard(
		@NonNull Context context,
		@NonNull DialogInterface.OnClickListener onKeepClickListener,
		@NonNull DialogInterface.OnClickListener onDiscardClickListener) {
		return prompt(
			context,
			R.string.common_prompt_discard_data,
			R.string.common_keep_editing,
			R.string.common_discard,
			onKeepClickListener,
			onDiscardClickListener);
	}

	public static AlertDialog prompt(
		@NonNull Context context,
		@StringRes int message,
		@StringRes int okButtonLabel,
		@StringRes int cancelButtonLabel,
		@NonNull DialogInterface.OnClickListener onOkClick,
		@NonNull DialogInterface.OnClickListener onCancelClick) {
		return new AlertDialog.Builder(context)
			.setMessage(message)
			.setPositiveButton(okButtonLabel, onOkClick)
			.setNegativeButton(cancelButtonLabel, onCancelClick)
			.create();
	}

	public static AlertDialog retry(
		@NonNull Context context,
		@StringRes int message,
		@NonNull DialogInterface.OnClickListener onOkClick,
		@NonNull DialogInterface.OnClickListener onCancelClick) {
		return new AlertDialog.Builder(context)
			.setMessage(message)
			.setPositiveButton(R.string.common_retry, onOkClick)
			.setNegativeButton(R.string.common_cancel, onCancelClick)
			.create();
	}

	public static Dialog singleChoice(@NonNull Context context, String title, String[] options, DialogInterface.OnClickListener onClickListener) {
		return new AlertDialog.Builder(context)
			.setTitle(title)
			.setItems(options, onClickListener)
			.create();
	}

	public static Dialog simpleOk(@NonNull Context context, @StringRes int title, @StringRes int message, DialogInterface.OnDismissListener onDismissListener) {
		return simpleOk(context, context.getString(title), context.getString(message), onDismissListener);
	}

	public static Dialog simpleOk(@NonNull Context context, @StringRes int title, @StringRes int message) {
		return simpleOk(context, context.getString(title), context.getString(message), null);
	}

	public static Dialog simpleOk(@NonNull Context context, String title, String message) {
		return simpleOk(context, title, message, null);
	}

	private static Dialog simpleOk(@NonNull Context context, String title, String message, DialogInterface.OnDismissListener onDismissListener) {
		return new AlertDialog.Builder(context)
			.setTitle(title)
			.setMessage(message)
			.setNeutralButton(R.string.dialog_action_ok, null)
			.setOnDismissListener(onDismissListener)
			.create();
	}

	public static void datePicker(@NonNull FragmentManager fragmentManager, @NonNull String currentDate) {
		Bundle bundle = new Bundle();
		bundle.putString(CalendarDatePickerDialog.BUNDLE_DATE, currentDate);

		DialogFragment datePicker = new CalendarDatePickerDialog();
		datePicker.setArguments(bundle);
		datePicker.show(fragmentManager, "datePicker");
	}

	public static void datePicker(@NonNull Fragment targetFragment, @NonNull FragmentManager fragmentManager, @NonNull String currentDate) {
		Bundle bundle = new Bundle();
		bundle.putString(CalendarDatePickerDialog.BUNDLE_DATE, currentDate);

		DialogFragment datePicker = new CalendarDatePickerDialog();
		datePicker.setTargetFragment(targetFragment, CalendarDatePickerDialog.REQUEST_CODE);
		datePicker.setArguments(bundle);
		datePicker.show(fragmentManager, "datePicker");
	}

	public static void timePicker(@NonNull FragmentManager fragmentManager, @NonNull String currentTime) {
		Bundle bundle = new Bundle();
		bundle.putString(ClockTimePickerDialog.BUNDLE_TIME, currentTime);

		DialogFragment timePicker = new ClockTimePickerDialog();
		timePicker.setArguments(bundle);
		timePicker.show(fragmentManager, "timePicker");
	}

	public static void timePicker(@NonNull Fragment targetFragment, @NonNull FragmentManager fragmentManager, @NonNull String currentTime) {
		Bundle bundle = new Bundle();
		bundle.putString(ClockTimePickerDialog.BUNDLE_TIME, currentTime);

		DialogFragment timePicker = new ClockTimePickerDialog();
		timePicker.setTargetFragment(targetFragment, ClockTimePickerDialog.REQUEST_CODE);
		timePicker.setArguments(bundle);
		timePicker.show(fragmentManager, "timePicker");
	}

	public static Dialog loading(@NonNull Context context, @StyleRes int style, @ColorRes int color) {
		return new TransparentProgressDialog(context, style, color);
	}

	public static void dismiss(@Nullable Dialog progressDialog) {
		if (progressDialog == null) {
			return;
		}

		progressDialog.dismiss();
	}
}
