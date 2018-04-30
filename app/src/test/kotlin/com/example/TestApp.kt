package com.example

import com.squareup.leakcanary.RefWatcher

class TestApp : App() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun enableLeakCanary(): RefWatcher {
        return RefWatcher.DISABLED
    }
}
