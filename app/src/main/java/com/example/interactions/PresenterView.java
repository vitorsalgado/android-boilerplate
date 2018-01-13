package com.example.interactions;

import com.example.uava.interfaces.RetryCallback;

public interface PresenterView {
	void showLoading();

	void loaded();

	void error(Throwable throwable);

	void errorWithRetry(Throwable throwable, RetryCallback retryAction);
}
