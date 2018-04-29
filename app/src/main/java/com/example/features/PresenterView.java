package com.example.features;

import com.example.toolkit.RetryCallback;

public interface PresenterView {
  void showLoading();

  void loaded();

  void error(Throwable throwable);

  void errorWithRetry(Throwable throwable, RetryCallback retryAction);
}
