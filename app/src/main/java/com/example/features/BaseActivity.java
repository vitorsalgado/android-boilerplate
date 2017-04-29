package com.example.features;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.R;
import com.example.utils.DialogUtils;
import com.example.utils.DimensionUtils;
import com.facebook.rebound.BaseSpringSystem;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;

public abstract class BaseActivity extends AppCompatActivity {
    @Nullable @BindView(R.id.toolbar_component) Toolbar mToolBar;
    @Nullable @BindView(R.id.error_action_component_holder) View mErrorActionHolder;
    @Nullable @BindView(R.id.error_action_button) Button mBtnErrorAction;
    @Nullable @BindView(R.id.error_action_text) TextView mTextViewErrorAction;

    @BindString(R.string.common_activate) String mStrActivate;

    @BindDrawable(R.drawable.ic_back) Drawable mIconBack;

    public static final SpringConfig SPRING_CONFIG = SpringConfig.fromOrigamiTensionAndFriction(50, 2);
    protected final BaseSpringSystem mSpringSystem = SpringSystem.create();

    private Dialog mProgressDialog;
    private boolean mIsErrorComponentAvailable;

    // BaseActivity event handlers

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsErrorComponentAvailable = mErrorActionHolder != null && mBtnErrorAction != null && mTextViewErrorAction != null;
    }

    @Override
    protected void onDestroy() {
        DialogUtils.dismiss(mProgressDialog);
        mSpringSystem.removeAllListeners();

        super.onDestroy();
    }

    // Toolbars

    protected void setUpToolBar() {
        setUpToolBar("");
    }

    protected void setUpToolBar(String title) {
        if (mToolBar == null) {
            return;
        }

        setSupportActionBar(mToolBar);

        final ActionBar supportActionBar = getSupportActionBar();

        if (supportActionBar == null) {
            throw new IllegalStateException("failed to setup supportActionBar. supportActionBar is null.");
        }

        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(mIconBack);
        supportActionBar.setTitle(title);
    }

    protected void makeStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            if (mToolBar != null) {
                mToolBar.setPadding(0, DimensionUtils.dpToPx(this, 15), 0, 0);
            }
        }
    }

    // Default Presenter View Methods

    public void showLoading() {
        mProgressDialog = DialogUtils.loading(this);
        mProgressDialog.show();
    }

    public void loaded() {
        DialogUtils.dismiss(mProgressDialog);
    }

    // Spring Animations

    public BaseSpringSystem getSpringSystem() {
        return mSpringSystem;
    }

    public static class ViewSpringListener extends SimpleSpringListener {
        View mView;

        public ViewSpringListener(@NonNull View view) {
            mView = view;
        }

        @Override
        public void onSpringUpdate(Spring spring) {
            float value = (float) spring.getCurrentValue();
            float scale = 1f - (value * 0.5f);

            mView.setScaleX(scale);
            mView.setScaleY(scale);
        }
    }

    // General

    protected String getTag() {
        return this.getClass().getSimpleName();
    }
}
