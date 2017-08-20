package com.example.features.main;

import android.support.annotation.NonNull;

import com.example.AppContext;
import com.example.features.BasePresenter;

class MainPresenter extends BasePresenter<MainView> {
	MainPresenter(@NonNull MainView view) {
		super(view);
	}

	void checkAuthentication() {
		getView().onAuthCheckResult(AppContext.isAuth());
	}
}
