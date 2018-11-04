package br.com.vitorsalgado.example.utils.delegates

import android.app.Activity
import android.app.Dialog
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
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
