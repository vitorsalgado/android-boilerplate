// package br.com.vitorsalgado.example.android.utils.base
//
// import android.support.v4.app.Fragment
// import br.com.vitorsalgado.example.App
// import br.com.vitorsalgado.example.BuildConfig
// import br.com.vitorsalgado.example.toolkit.RetryCallback
//
// open class AbstractFragment : Fragment(), PresenterView {
//  override fun onDestroy() {
//    if (BuildConfig.DEBUG) {
//      val refWatcher = App.refWatcher
//      refWatcher.watch(this)
//    }
//
//    super.onDestroy()
//  }
//
//  override fun showLoading() {
//
//  }
//
//  override fun loaded() {
//
//  }
//
//  override fun error(throwable: Throwable) {
//
//  }
//
//  override fun errorWithRetry(throwable: Throwable, retryAction: RetryCallback) {
//
//  }
// }
