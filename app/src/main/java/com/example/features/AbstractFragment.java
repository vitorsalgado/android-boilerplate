package com.example.features;

import android.support.v4.app.Fragment;

import com.example.App;
import com.example.BuildConfig;
import com.example.toolkit.RetryCallback;
import com.squareup.leakcanary.RefWatcher;

public class AbstractFragment extends Fragment implements PresenterView {
  @Override
  public void onDestroy() {
    if (BuildConfig.DEBUG) {
      RefWatcher refWatcher = App.getRefWatcher();
      refWatcher.watch(this);
    }

    super.onDestroy();
  }

  @Override
  public void showLoading() {

  }

  @Override
  public void loaded() {

  }

  @Override
  public void error(Throwable throwable) {

  }

  @Override
  public void errorWithRetry(Throwable throwable, RetryCallback retryAction) {

  }
}
