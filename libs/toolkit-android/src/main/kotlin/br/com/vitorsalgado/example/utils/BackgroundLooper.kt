package br.com.vitorsalgado.example.utils

import android.os.HandlerThread
import android.os.Looper
import android.os.Process

object BackgroundLooper {
  private var handlerThread: HandlerThread? = null

  fun get(): Looper {
    if (handlerThread == null) {
      handlerThread = HandlerThread("BackgroundHandlerThread", Process.THREAD_PRIORITY_BACKGROUND)
      handlerThread!!.start()
    }

    return handlerThread!!.looper
  }
}
