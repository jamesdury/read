package com.jamesdury.read

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.ByteArrayInputStream

class ShareActivity : AppCompatActivity() {
    private val TAG = this::class.java.simpleName
    private lateinit var pageResponse: String
    private val headers:Map<String, String> = object:HashMap<String, String>() {
        init{
            put("Connection", "close")
            put("Content-Type", "text/html")
            put("Access-Control-Allow-Origin", "*")
            put("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS")
            put("Access-Control-Max-Age", "600")
            put("Access-Control-Allow-Credentials", "true")
            put("Access-Control-Allow-Headers", "accept, authorization, Content-Type")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.share_activity)

        val action = intent.action
        val type = intent.type
        if (Intent.ACTION_SEND == action && type == "text/plain") {
            val response = intent.getStringExtra(Intent.EXTRA_TEXT).toString();
            initializeWebView(response)
        }
    }


    fun initializeWebView(requestUrl: String?) {
        // TODO issues with reinitialisation
        val myWebView: WebView = findViewById(R.id.webview)
        myWebView.settings.javaScriptEnabled = true
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.builtInZoomControls = true
        myWebView.settings.displayZoomControls = false

        myWebView.addJavascriptInterface(
            WebInterface(this),
            "Android"
        )
        myWebView.addJavascriptInterface(
            WebInterface(this),
            "AndroidErrorReporter"
        )

        myWebView.loadUrl("file:///android_asset/index.html");

        val queue = Volley.newRequestQueue(this)

        myWebView.setWebViewClient(object : WebViewClient() {
            // The webview has finished initialising,
            // lets display it and ask for it to request the content
            override fun onPageFinished(view: WebView, url: String) {
                val stringRequest = StringRequest(
                    Request.Method.GET,
                    requestUrl,
                    { response ->
                        response?.let {
                            pageResponse = it
                            myWebView.visibility = View.VISIBLE;
                            view.loadUrl("javascript:request('$requestUrl')");
                        } ?: run {
                            Log.e(TAG, "loadingerror")
                        }
                    },
                    {
                        Log.e(TAG, it.toString())
                        throw it;
                    })

                queue.add(stringRequest)
            }

            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? {
                // Anything that isnt our `fetch` request, let the webview handle
                if (request.url.toString().indexOf("dark-reader-request") == -1){
                    return super.shouldInterceptRequest(view, request);
                }
                // send the data back to the browser with CORS fixed
                return WebResourceResponse(
                    "text/plain",
                    "UTF-8",
                    200,
                    "OK",
                    headers,
                    ByteArrayInputStream(
                        pageResponse.toByteArray()
                    )
                )
            }
        })
    }
}
