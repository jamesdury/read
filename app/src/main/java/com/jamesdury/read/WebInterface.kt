package com.jamesdury.read;

import android.content.Context
import android.net.Uri
import android.webkit.JavascriptInterface
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent

/** Instantiate the interface and set the context  */
public class WebInterface(
        private val context: Context
) {
    @JavascriptInterface
    fun open(url: String?) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    @JavascriptInterface
    fun onError(error: String?) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
        throw Error(error)
    }
}
