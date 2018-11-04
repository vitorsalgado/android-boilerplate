package br.com.vitorsalgado.example.utils

import android.os.HandlerThread
import android.os.Looper
import android.os.Process

object ThreadUtils {
  private var handlerThread: HandlerThread? = null

  fun backgroundLooper(): Looper {
    if (handlerThread == null) {
      handlerThread = HandlerThread("BackgroundHandlerThread", Process.THREAD_PRIORITY_BACKGROUND)
      handlerThread!!.start()
    }

    return handlerThread!!.looper
  }
}
