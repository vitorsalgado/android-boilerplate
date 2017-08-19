package com.example.features.main;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;

import com.example.R;
import com.example.databinding.MainActivityBinding;
import com.example.features.BaseActivity;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    MainActivityBinding mBinding;
    int mCurrentTab;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
    }

    // TabLayout event handlers

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mBinding.tabPager.setCurrentItem(tab.getPosition());
        mCurrentTab = tab.getPosition();

        setTabColor(tab, ContextCompat.getColor(this, R.color.tab_seletected));
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabColor(tab, ContextCompat.getColor(this, R.color.tab_seletected));
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    // Helper methods

    private void setTabIcon(TabLayout.Tab tab, @DrawableRes int icon) {
        if (tab == null) {
            return;
        }

        tab.setIcon(icon);
    }

    private void setTabColor(TabLayout.Tab tab, int color) {
        if (tab == null || tab.getIcon() == null) {
            return;
        }

        tab.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    }

}
