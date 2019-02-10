package br.com.vitorsalgado.example.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object ActivityUtils {
  fun replace(fragmentManager: FragmentManager, fragment: Fragment, @IdRes frameId: Int) {
    val transaction = fragmentManager.beginTransaction()

    transaction.replace(frameId, fragment)
    transaction.commit()
  }

  fun openLink(context: Context, url: String) {
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
  }

  fun shareText(context: Context, title: String, textToShare: String) {
    val sendIntent = Intent()

    sendIntent.action = Intent.ACTION_SEND
    sendIntent.putExtra(Intent.EXTRA_TEXT, textToShare)
    sendIntent.type = "text/plain"

    context.startActivity(Intent.createChooser(sendIntent, title))
  }
}
