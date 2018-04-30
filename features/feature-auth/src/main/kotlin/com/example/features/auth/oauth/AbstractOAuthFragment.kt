package com.example.features.auth.oauth

//package com.example.features.auth
//
//import android.os.Bundle
//import android.text.TextUtils
//import android.util.Base64
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.webkit.JavascriptInterface
//import android.webkit.WebView
//
//import com.example.features.AbstractWebViewFragment
//
//import org.json.JSONException
//import org.json.JSONObject
//
//import java.nio.charset.Charset
//
//abstract class AbstractOAuthFragment : AbstractWebViewFragment(), OAuthView {
//    private var presenter: OAuthPresenter? = null
//    private var url: String? = null
//    private var resultOK: Boolean = false
//
//    protected abstract val oAuthServiceName: String
//
//    protected val stateString: String
//        get() {
//            try {
//                return Base64.encodeToString(JSONObject().put("loginStyle", "popup")
//                        .put("credentialToken", oAuthServiceName + System.currentTimeMillis())
//                        .put("isCordova", true)
//                        .toString()
//                        .toByteArray(Charset.forName("UTF-8")), Base64.NO_WRAP)
//            } catch (exception: JSONException) {
//                throw RuntimeException(exception)
//            }
//
//        }
//
//    protected abstract fun generateURL(oauthConfig: LoginServiceConfiguration): String
//
//    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
//
//        presenter = OAuthPresenter(this)
//        presenter!!.loadService(oAuthServiceName)
//
//        return binding.getRoot()
//    }
//
//    protected fun onPageLoaded(webview: WebView, url: String) {
//        super.onPageLoaded(webview, url)
//
//        if (url.contains("_oauth/$oAuthServiceName?close")) {
//            val jsHookUrl = "javascript:" + "window._synastry_hook.handleConfig(document.getElementById('config').innerText);"
//            webview.loadUrl(jsHookUrl)
//        }
//    }
//
//    fun showService(oauthConfig: LoginServiceConfiguration) {
//        url = generateURL(oauthConfig)
//
//        showWebView()
//    }
//
//    fun close() {
//        getActivity().finish()
//    }
//
//    fun showLoginDone() {
//        resultOK = true
//        onOAuthCompleted()
//    }
//
//    fun showLoginError() {
//        onOAuthCompleted()
//    }
//
//    private fun showWebView() {
//        if (TextUtils.isEmpty(url)) {
//            getActivity().finish()
//            return
//        }
//
//        val webView = getWebview()
//
//        if (webView == null) {
//            getActivity().finish()
//            return
//        }
//
//        resultOK = false
//        webView!!.getSettings().setUserAgentString("Chrome/56.0.0.0 Mobile")
//        webView!!.loadUrl(url)
//        webView!!.addJavascriptInterface(JSInterface({ result ->
//            // onPageFinish is called twice... Should ignore latter one.
//            if (resultOK) {
//                return@webView.addJavascriptInterface
//            }
//
//            //presenter.login(result);
//        }), "_example_hook")
//    }
//
//    protected fun onOAuthCompleted() {}
//
//    protected fun navigateToInitialPage(webview: WebView) {
//
//    }
//
//    @FunctionalInterface
//    private interface JSInterfaceCallback {
//        fun handleResult(result: JSONObject?)
//    }
//
//    private class JSInterface internal constructor(private val jsInterfaceCallback: JSInterfaceCallback) {
//
//        @JavascriptInterface
//        fun handleConfig(config: String) {
//            try {
//                jsInterfaceCallback.handleResult(JSONObject(config))
//            } catch (exception: Exception) {
//                jsInterfaceCallback.handleResult(null)
//            }
//
//        }
//    }
//}
