package br.com.vitorsalgado.example.features.main

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import br.com.vitorsalgado.example.R
import br.com.vitorsalgado.example.utils.AppUtils
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
  private var mCurrentTab = 0
  private var mDoubleBackToExitPressedOnce = false
  private val mDoubleBackHandler = Handler()
  private val mExitRunnable = { mDoubleBackToExitPressedOnce = false }
  private var mExitToast: Toast? = null

  companion object {
    private const val REQUEST_CODE_RECOVER_PLAY_SERVICES = 9001
    private const val REQUEST_CODE_RECOVER_PLAY_SERVICES_WITHOUT_CALLBACK = 9002
    private const val REQUEST_CODE_WRITE_STORAGE_PERMISSION = 9003

    fun newIntent(context: Context): Intent {
      val intent = Intent(context, MainActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

      return intent
    }
  }

  // region Activity Events

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (!AppUtils.checkPlayServices(this, REQUEST_CODE_RECOVER_PLAY_SERVICES)) {
      finish()
      return
    }

    setLayout()
  }

  override fun onSupportNavigateUp() = findNavController(R.id.navHost).navigateUp()

  override fun onBackPressed() {
    if (mDoubleBackToExitPressedOnce) {
      super.onBackPressed()

      if (mExitToast != null) {
        mExitToast!!.cancel()
      }

      finish()
      return
    }

    mDoubleBackToExitPressedOnce = true
    mExitToast = Toast.makeText(this, R.string.double_back_to_exit, Toast.LENGTH_SHORT)

    mExitToast!!.show()
    mDoubleBackHandler.postDelayed(mExitRunnable, 2000)
  }

  override fun onDestroy() {
    mDoubleBackHandler.removeCallbacks(mExitRunnable)
    super.onDestroy()
  }

  // endregion

  // region Helpers

  private fun setTabIcon(tab: TabLayout.Tab?, @DrawableRes icon: Int) {
    if (tab == null) {
      return
    }

    tab.setIcon(icon)
  }

  private fun setTabColor(tab: TabLayout.Tab?, color: Int) {
    tab?.icon?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
  }

  private fun setLayout() {
    setTheme(R.style.AppTheme)
    setContentView(R.layout.main_activity)
  }

  // endregion
}
