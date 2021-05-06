package com.bakiyem.surucu.project.activity.webView3D

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.utils.ext.semibold
import kotlinx.android.synthetic.main.activity_webview_3d.*

class CheckoutPaymentWVActivityNew: BaseActivity() {
    override fun getLayoutID(): Int = R.layout.activity_webview_3d

    override fun initVM() {

    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()
    }

    override fun initReq() {

    }

    override fun initVMListener() {

    }

    override fun onCreateMethod() {
        loadHtml(KEY_LINK)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadHtml(html: String){
        wv_payment.webViewClient = WebViewClient()
        wv_payment.webChromeClient = WebChromeClient()
        wv_payment.settings.javaScriptEnabled = true
        wv_payment.settings.domStorageEnabled = true
        wv_payment.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {

            }

            override fun onPageFinished(view: WebView?, url: String?) {

            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                onBackPressed()
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

        wv_payment.loadUrl(html)

        iv_back.setOnClickListener {
            onBackPressed()
        }
    }



    companion object {
        private var KEY_LINK = "link"
        @JvmStatic
        fun start(
            context: Context,
            html: String
        ) {
            val starter = Intent(context, CheckoutPaymentWVActivityNew::class.java)
            this.KEY_LINK = html
            context.startActivity(starter)
        }
    }
}