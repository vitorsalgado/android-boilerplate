package com.example.main

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.support.annotation.DrawableRes
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.R
import com.example.utils.AppUtils
import kotlinx.android.synthetic.main.main_activity.*
import java.util.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {
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

  //region Activity Events

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    if (!AppUtils.checkPlayServices(this, REQUEST_CODE_RECOVER_PLAY_SERVICES)) {
      finish()
      return
    }

    setLayout()
  }

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

  //endregion

  //region TabLayout Events

  override fun onTabSelected(tab: TabLayout.Tab) {
    tabPager.currentItem = tab.position
    mCurrentTab = tab.position

    setTabColor(tab, ContextCompat.getColor(this, R.color.tab_selected))
  }

  override fun onTabUnselected(tab: TabLayout.Tab) {
    setTabColor(tab, ContextCompat.getColor(this, R.color.tab_selected))
  }

  override fun onTabReselected(tab: TabLayout.Tab) {

  }

  //endregion

  //region Helpers

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

    val fragments = ArrayList<Fragment>()
    // Here we add the fragments for tabbed navigation
    val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)
    tabPager.adapter = viewPagerAdapter

    // Offscreen limit according to total tabs
    // binding.tabPager.setOffscreenPageLimit(<TOTAL_TABS>);

    tabs.setupWithViewPager(tabPager)
    tabs.tabGravity = TabLayout.GRAVITY_FILL
    tabs.addOnTabSelectedListener(this)

    tabPager.currentItem = mCurrentTab

    // Adding tab icons
    // setTabIcon(binding.tablayoutMain.getTabAt(0), <ICON_RESOURCE>);

    // Initializing tabs states
    // setTabColor(binding.tablayoutMain.getTabAt(0), ContextCompat.getColor(this, R.color.tab_unselected));
    // setTabColor(binding.tablayoutMain.getTabAt(mCurrentTab), ContextCompat.getColor(this, R.color.tab_seletected));
  }

  //endregion
}
