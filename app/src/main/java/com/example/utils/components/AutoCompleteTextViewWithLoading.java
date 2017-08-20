package com.example.utils.components;

import android.content.Context;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

public class AutoCompleteTextViewWithLoading extends AppCompatAutoCompleteTextView {
	private ProgressBar mLoadingIndicator;

	public AutoCompleteTextViewWithLoading(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setLoadingIndicator(ProgressBar progressBar) {
		mLoadingIndicator = progressBar;
	}

	@Override
	protected void performFiltering(CharSequence text, int keyCode) {
		if (mLoadingIndicator != null) {
			mLoadingIndicator.setVisibility(View.VISIBLE);
		}

		super.performFiltering(text, keyCode);
	}

	@Override
	public void onFilterComplete(int count) {
		if (mLoadingIndicator != null) {
			mLoadingIndicator.setVisibility(View.GONE);
		}

		super.onFilterComplete(count);
	}

}
