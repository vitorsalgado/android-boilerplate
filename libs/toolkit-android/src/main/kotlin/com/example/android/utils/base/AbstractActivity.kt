package com.example.android.utils.base

//package com.example.features
//
//import android.app.Dialog
//import android.support.annotation.StringRes
//import android.support.design.widget.Snackbar
//import android.support.v4.content.ContextCompat
//import android.support.v7.app.AppCompatActivity
//import android.support.v7.widget.Toolbar
//import android.view.View
//import com.example.R
//import com.example.Reporter
//import com.example.android.utils.AppUtils
//import com.example.android.utils.DialogUtils
//import com.example.toolkit.RetryCallback
//
//abstract class AbstractActivity : AppCompatActivity() {
//  private var progressDialog: Dialog? = null
//
//  protected val unexpectedErrorMessage: Int
//    @StringRes
//    get() = 0
//
//  protected abstract fun root(): View
//
//  //region Activity Events
//
//  override fun onResume() {
//    if (!AppUtils.hasNetworkConnection(applicationContext)) {
//      Snackbar.make(root(), R.string.error_no_internet_connection, Snackbar.LENGTH_LONG).show()
//    }
//
//    super.onResume()
//  }
//
//  override fun onBackPressed() {
//    DialogUtils.dismiss(progressDialog)
//    super.onBackPressed()
//  }
//
//  override fun onDestroy() {
//    DialogUtils.dismiss(progressDialog)
//    super.onDestroy()
//  }
//
//  @JvmOverloads
//  protected fun setUpToolBar(toolBar: Toolbar, title: String = "") {
//    setSupportActionBar(toolBar)
//
//    val supportActionBar = supportActionBar
//      ?: throw IllegalStateException("failed to setup supportActionBar. supportActionBar is null.")
//
//    supportActionBar.setDisplayHomeAsUpEnabled(true)
//    supportActionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back))
//    supportActionBar.title = title
//  }
//
//  //endregion
//
//  //region Common Presenter View Events
//
//  fun showLoading() {
//    progressDialog = DialogUtils.loading(this, R.style.TransparentProgressDialog, R.color.accent)
//    progressDialog!!.show()
//  }
//
//  fun loaded() {
//    DialogUtils.dismiss(progressDialog)
//  }
//
//  fun error(throwable: Throwable) {
//    Reporter.report(throwable)
//
//    DialogUtils.dismiss(progressDialog)
//    DialogUtils.simpleOk(this, R.string.error, unexpectedErrorMessage)
//  }
//
//  fun errorWithRetry(throwable: Throwable, retryAction: RetryCallback) {
//    Reporter.report(throwable)
//
//    DialogUtils.dismiss(progressDialog)
//
//    DialogUtils.retry(this,
//      unexpectedErrorMessage,
//      { dialog, which ->
//        retryAction.retry()
//        dialog.dismiss()
//      },
//      { dialog, which -> dialog.dismiss() })
//      .show()
//  }
//
//  //endregion
//}//endregion
////region Common Components Setup
