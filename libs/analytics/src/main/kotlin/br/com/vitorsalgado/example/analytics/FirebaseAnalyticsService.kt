package br.com.vitorsalgado.example.analytics

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsService(private val activityProvider: ActivityProvider) : AnalyticsService {
  private val TAG: String = this.javaClass.simpleName

  override fun trackAction(context: Context, args: TraceActionArgs) {
    val bundle = Bundle()
    args.params.forEach {
      when (it.value) {
        is String -> bundle.putString(it.key, it.value as String)
        is Int -> bundle.putInt(it.key, it.value as Int)
        is Double -> bundle.putDouble(it.key, it.value as Double)
        is Parcelable -> bundle.putParcelable(it.key, it.value as Parcelable)
        else -> bundle.putString(it.key, it.value.toString())
      }
    }

    FirebaseAnalytics.getInstance(context).logEvent(args.action, bundle)
  }

  override fun trackView(context: Context, args: TraceScreenArgs) {
    FirebaseAnalytics.getInstance(context)
      .setCurrentScreen(activityProvider.currentActivity(), args.screen, null)
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as FirebaseAnalyticsService

    if (TAG != other.TAG) return false

    return true
  }

  override fun hashCode(): Int {
    return TAG.hashCode()
  }
}
