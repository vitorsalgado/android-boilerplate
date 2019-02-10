package br.com.vitorsalgado.example.analytics

import android.app.Activity
import android.content.Context
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class AnalyticsTest {
  val analyticsAdapter1: AnalyticsAdapter = mock(AnalyticsAdapter::class.java)
  val analyticsAdapter2: AnalyticsAdapter = mock(AnalyticsAdapter::class.java)
  val context: Context = mock(Context::class.java)
  val activity: Activity = mock(Activity::class.java)

  fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
  }

  private fun <T> uninitialized(): T = null as T

  @Before
  fun setup() {
    AnalyticsUtils.attach(analyticsAdapter1)
    AnalyticsUtils.attach(analyticsAdapter2)

    doNothing().`when`(analyticsAdapter1).trackAction(any(), any())
  }

  @Test
  fun `it should call all registered analytics services to track action`() {
    AnalyticsUtils.trackAction(context, "action")

    verify(analyticsAdapter1).trackAction(any(), any())
    verify(analyticsAdapter2).trackAction(any(), any())
  }

  @Test
  fun `it should call all registered analytics when tracking screen`() {
    AnalyticsUtils.trackView(activity, TraceScreenArgs("the screen"))

    verify(analyticsAdapter1).trackView(any(), any())
    verify(analyticsAdapter2).trackView(any(), any())
  }

  @Test
  fun `it should remove the correct service when calling "detach"`() {
    AnalyticsUtils.detach(analyticsAdapter1)

    AnalyticsUtils.trackAction(context, "action")

    verify(analyticsAdapter1, times(0)).trackAction(any(), any())
    verify(analyticsAdapter2).trackAction(any(), any())
  }

  @Test
  fun `it should remove all services when calling "detachAll"`() {
    AnalyticsUtils.detachAll()

    AnalyticsUtils.trackAction(context, "action")

    verify(analyticsAdapter1, times(0)).trackAction(any(), any())
    verify(analyticsAdapter2, times(0)).trackAction(any(), any())
  }
}
