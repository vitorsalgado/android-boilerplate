package br.com.test.analytics;

import android.support.annotation.NonNull;

@FunctionalInterface
public interface TraceableScreen {
	@NonNull
	@Screen
	String getScreenName();
}
