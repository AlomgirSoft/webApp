package com.example.webviewapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebViewActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var progressBar: ProgressBar

    private var webUri="https://sites.google.com/view/sirajganj-hub/home"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView= findViewById<WebView>(R.id.webView)
        progressBar= findViewById<ProgressBar>(R.id.progressBar2)


        setupWebView()


        webView.loadUrl(webUri)

    }


    private fun setupWebView() {
        with(webView) {
            settings.apply {
                javaScriptEnabled = true // Enable JavaScript for interactive content
                domStorageEnabled = true // Support modern web apps with DOM storage
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK // Enable caching
                useWideViewPort = true
                loadWithOverviewMode = true
            }

            // Set WebViewClient to manage loading within the app
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    progressBar.visibility = View.VISIBLE // Show loading indicator
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    progressBar.visibility = View.GONE // Hide loading indicator
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    // Handle loading error, you can show an error message or retry
                }
            }

            // Set WebChromeClient to support JavaScript alerts and other features
            webChromeClient = WebChromeClient()
        }
    }


    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}