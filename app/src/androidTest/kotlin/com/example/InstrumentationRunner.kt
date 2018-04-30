package com.example

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnitRunner

import com.linkedin.android.testbutler.TestButler

class InstrumentationRunner : AndroidJUnitRunner() {
  @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
  override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
    return Instrumentation.newApplication(InstrumentedApp::class.java, context)
  }

  override fun onStart() {
    TestButler.setup(InstrumentationRegistry.getTargetContext())
    super.onStart()
  }

  override fun finish(resultCode: Int, results: Bundle) {
    TestButler.teardown(InstrumentationRegistry.getTargetContext())
    super.finish(resultCode, results)
  }
}
