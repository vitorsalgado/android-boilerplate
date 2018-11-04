package br.com.vitorsalgado.example.analytics

import android.app.Activity
import android.content.Context
import java.util.*

object AnalyticsUtils {
  private val subscriber = mutableListOf<AnalyticsService>()

  fun attach(observer: AnalyticsService) {
    if (subscriber.contains(observer))
      throw IllegalArgumentException("observer of type ${observer.javaClass.simpleName} already added")

    subscriber.add(observer)
  }

  fun detach(observer: AnalyticsService) {
    subscriber.remove(observer)
  }

  fun detachAll() {
    subscriber.clear()
  }

  fun trackAction(context: Context, @Action action: String) {
    trackAction(context, TraceActionArgs(action, Collections.emptyMap()))
  }

  fun trackAction(context: Context, args: TraceActionArgs) {
    subscriber.forEach { it.trackAction(context, args) }
  }

  fun trackView(activity: Activity, @Screen screen: String) {
    trackView(activity, TraceScreenArgs(screen, Collections.emptyMap()))
  }

  fun trackView(activity: Activity, args: TraceScreenArgs) {
    subscriber.forEach { it.trackView(activity, args) }
  }
}
