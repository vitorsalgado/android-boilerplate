package br.com.vitorsalgado.example.utils.ui

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import br.com.vitorsalgado.example.utils.R
import br.com.vitorsalgado.example.utils.delegates.viewWithId

class TransparentProgressDialog(context: Context, @StyleRes style: Int, @ColorRes color: Int) : Dialog(context, style) {
  private val root: RelativeLayout by viewWithId(R.id.root)
  private val loading: ProgressBar by viewWithId(R.id.loading)

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
