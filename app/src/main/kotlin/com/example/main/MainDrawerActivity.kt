package com.example.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.example.R
import com.example.utils.AppUtils
import com.example.utils.extensions.setToolbar
import kotlinx.android.synthetic.main.main_drawer_activity.*

class MainDrawerActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (!AppUtils.checkPlayServices(this, REQUEST_CODE_RECOVER_PLAY_SERVICES)) {
      finish()
      return
    }

    setLayout()

    val toggle = ActionBarDrawerToggle(
      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

    drawer.addDrawerListener(toggle)
    toggle.syncState()
    nav.setNavigationItemSelectedListener(this)

  }

  override fun onBackPressed() {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START)
      return
    }


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

  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    drawer.closeDrawer(GravityCompat.START)
    return true
  }

  private fun setLayout() {
    setTheme(R.style.AppTheme)
    setContentView(R.layout.main_drawer_activity)
    setToolbar(toolbar, R.string.app_name)
  }
}
