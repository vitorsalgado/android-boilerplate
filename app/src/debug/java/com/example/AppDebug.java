package com.example;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

public class AppDebug extends App {
    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.DEVELOPER_ERRORS);

        Stetho.initializeWithDefaults(this);
    }

    @Override
    protected RefWatcher setUpLeakCanary() {
        return LeakCanary.refWatcher(this)
                .buildAndInstall();
    }

}
