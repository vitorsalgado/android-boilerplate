package br.com.vitorsalgado.example.analytics

import android.content.Context

interface AnalyticsService {
  fun trackAction(context: Context, args: TraceActionArgs)

  fun trackView(context: Context, args: TraceScreenArgs)

  override fun hashCode(): Int

  override fun equals(other: Any?): Boolean
}
