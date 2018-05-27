// package br.com.vitorsalgado.example.features
//
// import android.annotation.SuppressLint
// import android.annotation.TargetApi
// import android.databinding.DataBindingUtil
// import android.graphics.Bitmap
// import android.os.Build
// import android.os.Bundle
// import android.os.Message
// import android.view.LayoutInflater
// import android.view.View
// import android.view.ViewGroup
// import android.webkit.WebResourceError
// import android.webkit.WebResourceRequest
// import android.webkit.WebView
// import android.webkit.WebViewClient
// import br.com.vitorsalgado.example.R
// import br.com.vitorsalgado.example.databinding.WebviewBinding
//
// abstract class AbstractWebViewFragment : AbstractFragment() {
//  private var isSet = false
//  private val webviewClient = object : WebViewClient() {
//    private var error: Boolean = false
//
//    override fun onPageStarted(webview: WebView, url: String, favicon: Bitmap) {
//      error = false
//    }
//
//    override fun onPageFinished(webview: WebView, url: String) {
//      if (!error) {
//        onPageLoaded(webview, url)
//      }
//    }
//
//    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
//      super.onReceivedError(view, request, error)
//      this.error = true
//    }
//
//    override fun shouldOverrideUrlLoading(webview: WebView, url: String): Boolean {
//      return shouldOverride(webview, url) && onHandleCallback(webview, url) || super.shouldOverrideUrlLoading(webview, url)
//    }
//
//    @TargetApi(Build.VERSION_CODES.N)
//    override fun shouldOverrideUrlLoading(webview: WebView, request: WebResourceRequest): Boolean {
//      val uri = request.url.toString()
//
//      return shouldOverride(webview, uri) && onHandleCallback(webview, uri) || super.shouldOverrideUrlLoading(webview, uri)
//    }
//
//
//    override fun onFormResubmission(view: WebView, dontResend: Message, resend: Message) {
//      //resend POST request without confirmation.
//      resend.sendToTarget()
//    }
//  }
//
//  protected val webview: WebView
//    get() {
//      setupWebView()
//      return webview
//    }
//
//  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//    super.onCreateView(inflater, container, savedInstanceState)
//
//    binding = DataBindingUtil.inflate(layoutInflater, R.layout.webview, container, false)
//
//    setupWebView()
//    navigateToInitialPage(binding.webview)
//
//    return binding.root
//  }
//
//  @SuppressLint("SetJavaScriptEnabled")
//  private fun setupWebView() {
//    if (isSet) {
//      return
//    }
//
//    val settings = binding.webview.settings
//
//    if (settings != null) {
//      settings.javaScriptEnabled = true
//    }
//
//    binding.webview.isHorizontalScrollBarEnabled = false
//    binding.webview.webViewClient = webviewClient
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//      WebView.setWebContentsDebuggingEnabled(true)
//    }
//    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN) {
//      //refs: https://code.google.com/p/android/issues/detail?id=35288
//      binding.webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
//    }
//
//    isSet = true
//  }
//
//  protected abstract fun navigateToInitialPage(webview: WebView)
//
//  protected fun onPageLoaded(webview: WebView, url: String) {}
//
//  protected fun shouldOverride(webview: WebView, url: String): Boolean {
//    return false
//  }
//
//  protected fun onHandleCallback(webview: WebView, url: String): Boolean {
//    return false
//  }
// }
