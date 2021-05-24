package com.scarecrow.ragdoll.topskin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.*

class MainActivity : AppCompatActivity() {

    lateinit var myWebView: WebView
    lateinit var cookieManager: CookieManager
    lateinit var webStorage: WebStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        myWebView = WebView(baseContext)
        val settings: WebSettings = myWebView.getSettings()
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        myWebView.webViewClient = WebViewClient()
//        myWebView.addJavascriptInterface(MyJavascriptInterface(this), MyJavascriptInterface.TAG)
        cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)

        webStorage = WebStorage.getInstance()
        myWebView.loadUrl("https://topskin.co")
        setContentView(myWebView)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        webStorage.getOrigins {
            return@getOrigins
        }
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            cookieManager.flush()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}