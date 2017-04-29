package com.example.utils.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.R;

public class TransparentProgressDlg extends Dialog {

    @SuppressLint("InflateParams")
    public TransparentProgressDlg(Context context) {
        super(context, R.style.TransparentProgressDialog);

        if (getWindow() == null) {
            return;
        }

        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;

        getWindow().setAttributes(wlmp);

        setTitle(null);

        setCancelable(false);
        setOnCancelListener(null);

        View view = getLayoutInflater().inflate(R.layout.component_progress_dialog, null);
        ProgressBar spinner = (ProgressBar) view.findViewById(R.id.progress_bar_custom);
        spinner.getIndeterminateDrawable().setColorFilter(0xFFA94063, android.graphics.PorterDuff.Mode.MULTIPLY);

        addContentView(view, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
    }

}
