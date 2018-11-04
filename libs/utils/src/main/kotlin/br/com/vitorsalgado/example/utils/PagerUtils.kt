package br.com.vitorsalgado.example.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

object PagerUtils {
  fun createPager(context: Context, indicators: Array<ImageView>, container: ViewGroup, @DrawableRes selected: Int, @DrawableRes unselected: Int) {
    container.removeAllViews()

    for (i in indicators.indices) {
      indicators[i] = ImageView(context)
      indicators[i].setImageDrawable(ContextCompat.getDrawable(context, unselected))

      val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
      params.leftMargin = 10

      indicators[i].layoutParams = params

      container.addView(indicators[i])
    }

    indicators[0].setImageDrawable(ContextCompat.getDrawable(context, selected))
  }

  fun markPage(context: Context, currentPage: Int, indicators: Array<ImageView>, @DrawableRes selected: Int, @DrawableRes unselected: Int) {
    val total = indicators.size

    if (currentPage + 1 > total) {
      return
    }

    for (indicator in indicators) {
      indicator.setImageDrawable(ContextCompat.getDrawable(context, unselected))
    }

    indicators[currentPage].setImageDrawable(ContextCompat.getDrawable(context, selected))
  }
}
