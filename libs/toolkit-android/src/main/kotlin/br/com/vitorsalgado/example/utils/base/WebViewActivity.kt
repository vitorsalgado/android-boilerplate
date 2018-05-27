// package br.com.vitorsalgado.example.features
//
// import android.content.Context
// import android.content.Intent
// import android.databinding.DataBindingUtil
// import android.os.Bundle
// import android.support.annotation.StringRes
// import android.support.v4.content.ContextCompat
// import android.support.v7.app.AppCompatActivity
// import android.webkit.WebResourceRequest
// import android.webkit.WebView
// import android.webkit.WebViewClient
// import br.com.vitorsalgado.example.R
// import br.com.vitorsalgado.example.databinding.WebviewBinding
//
// class WebViewActivity : AppCompatActivity() {
//  internal var binding: WebviewBinding
//  private var title: String? = null
//  private var url: String? = null
//
//  public override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//
//    binding = DataBindingUtil.setContentView(this, R.layout.webview)
//
//    if (intent.extras == null) {
//      throw IllegalStateException("WebViewActivity must be initialized with title and url extras.")
//    }
//
//    title = intent.extras!!.getString(TITLE_PAGE)
//    url = intent.extras!!.getString(URL_PAGE)
//
//    setSupportActionBar(binding.toolbar)
//
//    val supportActionBar = supportActionBar
//      ?: throw IllegalStateException("failed to setup supportActionBar. supportActionBar is null.")
//
//    supportActionBar.setDisplayHomeAsUpEnabled(true)
//    supportActionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back))
//    supportActionBar.title = title
//
//    setupWebView()
//
//    binding.toolbar.setNavigationOnClickListener { v -> onBackPressed() }
//  }
//
//  override fun onBackPressed() {
//    finish()
//    super.onBackPressed()
//  }
//
//  private fun setupWebView() {
//    binding.webview.loadUrl(url)
//    binding.webview.webViewClient = object : WebViewClient() {
//      override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//        view.loadUrl(url)
//        return true
//      }
//
//      override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
//        return true
//      }
//    }
//  }
//
//  public override fun onSaveInstanceState(outState: Bundle) {
//    super.onSaveInstanceState(outState)
//
//    outState.putString(URL_PAGE, url)
//    outState.putString(TITLE_PAGE, title)
//  }
//
//  companion object {
//    val TITLE_PAGE = "TITLE_PAGE"
//    val URL_PAGE = "URL_PAGE"
//
//    fun newIntent(context: Context, @StringRes title: Int, @StringRes url: Int): Intent {
//      val intent = Intent(context, WebViewActivity::class.java)
//      intent.putExtra(TITLE_PAGE, title)
//      intent.putExtra(URL_PAGE, url)
//
//      return intent
//    }
//  }
// }
