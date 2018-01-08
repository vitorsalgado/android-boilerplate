package com.example;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class InstrumentationRunner extends AndroidJUnitRunner {
	@Override
	public Application newApplication(ClassLoader cl, String className, Context context) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return newApplication(InstrumentedApp.class, context);
	}
}
