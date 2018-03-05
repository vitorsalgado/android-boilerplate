package com.example.features.authentication;

import android.support.annotation.NonNull;

import com.example.App;
import com.example.R;
import com.example.features.ViewPresenter;

class OAuthPresenter extends ViewPresenter<OAuthView> {
	OAuthPresenter(@NonNull OAuthView view) {
		super(view);
	}

	void loadService(String serviceName) {
		String fbID = App.getInstance().getResources().getString(R.string.facebook_app_id);
		String service = "facebook";

		getView().showService(new LoginServiceConfiguration("", service, fbID));
	}
}
