package com.example.android.utils.widgets;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.android.R;
import com.example.android.databinding.ComponentProgressDialogBinding;

public class TransparentProgressDialog extends Dialog {
	public TransparentProgressDialog(@NonNull Context context, @StyleRes int style, @ColorRes int color) {
		super(context, style);

		if (getWindow() == null) {
			return;
		}

		WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
		layoutParams.gravity = Gravity.CENTER_HORIZONTAL;

		getWindow().setAttributes(layoutParams);

		setTitle(null);
		setCancelable(false);
		setOnCancelListener(null);

		ComponentProgressDialogBinding binding =
			DataBindingUtil.inflate(getLayoutInflater(), R.layout.component_progress_dialog, null, false);

		binding.loading.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), color), android.graphics.PorterDuff.Mode.MULTIPLY);
		addContentView(binding.getRoot(), new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
	}
}
