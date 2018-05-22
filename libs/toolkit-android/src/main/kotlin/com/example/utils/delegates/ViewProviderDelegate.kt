package com.example.utils.delegates

import android.app.Activity
import android.app.Dialog
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlin.reflect.KProperty

open class ViewProviderDelegate<T : View>(@IdRes private val idRes: Int) {
  private var view: T? = null

  open operator fun getValue(thisRef: Activity, property: KProperty<*>): T {
    view?.let { if (!it.isAttachedToWindow) view = null }
    view = view ?: thisRef.findViewById(idRes)
    return view!!
  }

  open operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
    view?.let { if (!it.isAttachedToWindow) view = null }
    view = view ?: thisRef.view?.findViewById(idRes)
    return view!!
  }

  open operator fun getValue(thisRef: Dialog, property: KProperty<*>): T {
    view?.let { if (!it.isAttachedToWindow) view = null }
    view = view ?: thisRef.findViewById(idRes)
    return view!!
  }

  open operator fun getValue(thisRef: View, property: KProperty<*>): T {
    view = view ?: thisRef.findViewById(idRes)
    return view!!
  }

  open operator fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T {
    view = view ?: thisRef.itemView.findViewById(idRes)
    return view!!
  }
}

fun <T : View> viewWithId(@IdRes idRes: Int) = ViewProviderDelegate<T>(idRes)
