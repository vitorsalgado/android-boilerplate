package com.example.utils

import timber.log.Timber

object LogUtility {
  private val tag: String
    get() {
      val elements = Thread.currentThread().stackTrace

      return if (elements.size >= 5) {
        getSimpleName(elements[4].className)
      } else {
        "[ com.example ]"
      }
    }

  fun d(log: String, vararg args: Any) {
    Timber.tag(tag).d(log, *args)
  }

  fun d(throwable: Throwable) {
    Timber.tag(tag).d(throwable)
  }

  fun d(throwable: Throwable, log: String, vararg args: Any) {
    Timber.tag(tag).d(throwable, log, *args)
  }

  fun w(log: String, vararg args: Any) {
    Timber.tag(tag).w(log, *args)
  }

  fun w(throwable: Throwable) {
    Timber.tag(tag).w(throwable)
  }

  fun w(throwable: Throwable, log: String, vararg args: Any) {
    Timber.tag(tag).w(throwable, log, *args)
  }

  fun e(log: String, vararg args: Any) {
    Timber.tag(tag).e(log, *args)
  }

  fun e(throwable: Throwable) {
    Timber.tag(tag).e(throwable)
  }

  fun e(throwable: Throwable, log: String, vararg args: Any) {
    Timber.tag(tag).e(throwable, log, *args)
  }

  private fun getSimpleName(className: String): String {
    val idx = className.lastIndexOf('.')
    return className.substring(idx + 1)
  }
}
