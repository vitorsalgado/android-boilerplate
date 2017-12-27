package com.example.interactions;

import br.com.vitorsalgado.androidstarter.uava.interfaces.RetryCallback;

public interface AbstractView {
	void showLoading();

	void loaded();

	void error(Throwable throwable);

	void errorWithRetry(Throwable throwable, RetryCallback retryAction);
}
