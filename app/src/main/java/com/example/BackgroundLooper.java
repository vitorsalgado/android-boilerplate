package com.example;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.support.annotation.Nullable;

public final class BackgroundLooper {
	@Nullable
	private static HandlerThread handlerThread;

	public static Looper get() {
		if (handlerThread == null) {
			handlerThread = new HandlerThread("BackgroundHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
			handlerThread.start();
		}

		return handlerThread.getLooper();
	}
}
