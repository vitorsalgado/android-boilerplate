package br.com.vitorsalgado.example.analytics

import android.os.Binder
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

fun toBundle(params: Map<String, Any>): Bundle =
    with(Bundle()) {
        params.forEach {
            when (it.value) {
                is String -> putString(it.key, it.value as String)
                is Int -> putInt(it.key, it.value as Int)
                is Float -> putFloat(it.key, it.value as Float)
                is Byte -> putByte(it.key, it.value as Byte)
                is Double -> putDouble(it.key, it.value as Double)
                is Parcelable -> putParcelable(it.key, it.value as Parcelable)
                is CharArray -> putCharArray(it.key, it.value as CharArray)
                is ByteArray -> putByteArray(it.key, it.value as ByteArray)
                is Binder -> putBinder(it.key, it.value as Binder)
                is Bundle -> putBundle(it.key, it.value as Bundle)
                is Serializable -> putSerializable(it.key, it.value as Serializable)
                else -> putString(it.key, it.value.toString())
            }
        }

        return this
    }
