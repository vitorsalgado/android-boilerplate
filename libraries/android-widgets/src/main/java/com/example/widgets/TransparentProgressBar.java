package com.example.widgets;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.widgets.databinding.ComponentProgressDialogBinding;

public class TransparentProgressBar extends Dialog {
	public TransparentProgressBar(@NonNull Context context, @StyleRes int style, @ColorRes int color) {
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

		binding.customProgressbar.getIndeterminateDrawable().setColorFilter(getContext().getResources().getColor(color), android.graphics.PorterDuff.Mode.MULTIPLY);

		addContentView(binding.getRoot(), new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
	}
}
