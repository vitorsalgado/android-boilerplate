package com.example.android.utils

import android.util.Log
import com.example.android.BuildConfig

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
		if (BuildConfig.DEBUG) {
			Log.d(tag, String.format(log, *args))
		}
	}

	fun d(throwable: Throwable) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, throwable.message, throwable)
		}
	}

	fun d(throwable: Throwable, log: String, vararg args: Any) {
		if (BuildConfig.DEBUG) {
			Log.d(tag, String.format(log, *args), throwable)
		}
	}

	fun w(log: String, vararg args: Any) {
		if (BuildConfig.DEBUG) {
			Log.w(tag, String.format(log, *args))
		}
	}

	fun w(throwable: Throwable) {
		if (BuildConfig.DEBUG) {
			Log.w(tag, throwable.message, throwable)
		}
	}

	fun w(throwable: Throwable, log: String, vararg args: Any) {
		if (BuildConfig.DEBUG) {
			Log.w(tag, String.format(log, *args), throwable)
		}
	}

	fun e(log: String, vararg args: Any) {
		if (BuildConfig.DEBUG) {
			Log.e(tag, String.format(log, *args))
		}
	}

	fun e(throwable: Throwable) {
		if (BuildConfig.DEBUG) {
			Log.e(tag, throwable.message, throwable)
		}
	}

	fun e(throwable: Throwable, log: String, vararg args: Any) {
		if (BuildConfig.DEBUG) {
			Log.e(tag, String.format(log, *args), throwable)
		}
	}

	private fun getSimpleName(className: String): String {
		val idx = className.lastIndexOf('.')
		return className.substring(idx + 1)
	}
}
