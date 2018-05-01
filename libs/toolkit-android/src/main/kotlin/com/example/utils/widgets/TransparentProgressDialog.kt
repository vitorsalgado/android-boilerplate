package com.example.utils.widgets

import android.app.Dialog
import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.StyleRes
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.RelativeLayout
import com.example.android.R
import kotlinx.android.synthetic.main.component_progress_dialog.*

class TransparentProgressDialog(context: Context, @StyleRes style: Int, @ColorRes color: Int) : Dialog(context, style) {
  init {
    val layoutParams = window!!.attributes
    layoutParams.gravity = Gravity.CENTER_HORIZONTAL

    window!!.attributes = layoutParams

    setTitle(null)
    setCancelable(false)
    setOnCancelListener(null)

    setContentView(R.layout.component_progress_dialog)

    loading.indeterminateDrawable.setColorFilter(ContextCompat.getColor(getContext(), color), android.graphics.PorterDuff.Mode.MULTIPLY)
    addContentView(root, RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT))
  }
}
