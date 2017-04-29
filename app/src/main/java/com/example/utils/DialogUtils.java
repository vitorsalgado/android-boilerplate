package com.example.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import com.example.R;
import com.example.utils.dialogs.DatePickDlg;
import com.example.utils.dialogs.TimePickDlg;
import com.example.utils.dialogs.TransparentProgressDlg;

public final class DialogUtils {
    public static Dialog promptToDiscard(@NonNull Context contex, @Nullable DialogInterface.OnClickListener onKeepClickListener, @Nullable DialogInterface.OnClickListener onDiscardClickListener) {
        return prompt(
                contex,
                R.string.common_propmt_discard_data,
                R.string.common_keep_editing,
                R.string.common_discard,
                onKeepClickListener,
                onDiscardClickListener);
    }

    public static Dialog prompt(
            @NonNull Context contex,
            @StringRes int message,
            @StringRes int okButtonLabel,
            @StringRes int cancelButtonLabel,
            @Nullable DialogInterface.OnClickListener onOkClick,
            @Nullable DialogInterface.OnClickListener onCancelClick) {
        return new AlertDialog.Builder(contex)
                .setMessage(contex.getString(message))
                .setPositiveButton(contex.getString(okButtonLabel), onOkClick)
                .setNegativeButton(contex.getString(cancelButtonLabel), onCancelClick)
                .create();
    }

    public static Dialog singleChoice(@NonNull Context context, String title, String[] options, DialogInterface.OnClickListener onClickListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setItems(options, onClickListener)
                .create();
    }

    public static Dialog simpleOk(Context context, String title, String message) {
        return simpleOk(context, title, message, null);
    }

    public static Dialog simpleOk(Context context, String title, String message, AlertDialog.OnDismissListener onDismissListener) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null)
                .setOnDismissListener(onDismissListener)
                .create();
    }

    public static void datePicker(@NonNull FragmentManager fragmentManager, @NonNull String currentDate) {
        Bundle bundle = new Bundle();
        bundle.putString(DatePickDlg.BUNDLE_DATE, currentDate);

        DialogFragment datePicker = new DatePickDlg();
        datePicker.setArguments(bundle);
        datePicker.show(fragmentManager, "datePicker");
    }

    public static void datePicker(@NonNull Fragment targetFragment, @NonNull FragmentManager fragmentManager, @NonNull String currentDate) {
        Bundle bundle = new Bundle();
        bundle.putString(DatePickDlg.BUNDLE_DATE, currentDate);

        DialogFragment datePicker = new DatePickDlg();
        datePicker.setTargetFragment(targetFragment, DatePickDlg.REQUEST_CODE);
        datePicker.setArguments(bundle);
        datePicker.show(fragmentManager, "datePicker");
    }

    public static void timePicker(@NonNull FragmentManager fragmentManager, @NonNull String currentTime) {
        Bundle bundle = new Bundle();
        bundle.putString(TimePickDlg.BUNDLE_TIME, currentTime);

        DialogFragment timePicker = new TimePickDlg();
        timePicker.setArguments(bundle);
        timePicker.show(fragmentManager, "timePicker");
    }

    public static void timePicker(@NonNull Fragment targetFragment, @NonNull FragmentManager fragmentManager, @NonNull String currentTime) {
        Bundle bundle = new Bundle();
        bundle.putString(TimePickDlg.BUNDLE_TIME, currentTime);

        DialogFragment timePicker = new TimePickDlg();
        timePicker.setTargetFragment(targetFragment, TimePickDlg.REQUEST_CODE);
        timePicker.setArguments(bundle);
        timePicker.show(fragmentManager, "timePicker");
    }

    public static Dialog loading(@NonNull Context context) {
        return new TransparentProgressDlg(context);
    }

    public static void dismiss(@Nullable Dialog progressDialog) {
        if (progressDialog == null) {
            return;
        }

        progressDialog.dismiss();
    }
}
