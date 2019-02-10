package br.com.vitorsalgado.example.analytics

import android.app.Activity
import android.content.Context
import java.util.*

object AnalyticsUtils {
  private val subscriber = mutableListOf<AnalyticsAdapter>()

  fun attach(observer: AnalyticsAdapter) {
    if (subscriber.contains(observer))
      throw IllegalArgumentException("observer of type ${observer.javaClass.simpleName} already added")

    subscriber.add(observer)
  }

  fun detach(observer: AnalyticsAdapter) = subscriber.remove(observer)

  fun detachAll() = subscriber.clear()

  fun trackAction(context: Context, action: String) =
    trackAction(
      context,
      TraceActionArgs(action, Collections.emptyMap())
    )

  fun trackAction(context: Context, args: TraceActionArgs) =
    subscriber.forEach { it.trackAction(context, args) }

  fun trackView(context: Context, args: TraceScreenArgs) =
    subscriber.forEach { it.trackView(context, args) }
}
