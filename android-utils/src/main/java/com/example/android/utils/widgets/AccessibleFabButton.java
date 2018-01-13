package com.example.android.utils.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

public class AccessibleFabButton extends FloatingActionButton {
	public AccessibleFabButton(@NonNull Context context) {
		super(context);
	}

	public AccessibleFabButton(@NonNull Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AccessibleFabButton(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}
}
