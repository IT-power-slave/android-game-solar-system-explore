package com.example.mathsafari.ui

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebGLContainer(
    modifier: Modifier = Modifier,
    mode: GameMode,
    onBridgeCreated: (WebView) -> Unit,
    bridge: GameBridge
) {
    val webViewClient = remember { WebViewClient() }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                settings.domStorageEnabled = true
                settings.databaseEnabled = true
                settings.setSupportZoom(false)
                addJavascriptInterface(bridge, "GameBridge")
                this.webViewClient = webViewClient
                loadUrl("file:///android_asset/web/solar_system.html")
                onBridgeCreated(this)
            }
        },
        update = { webView ->
            // Handle updates if needed
        }
    )

    LaunchedEffect(mode) {
        // We'll call this from the ViewModel or Activity to ensure timing is right,
        // but this is a placeholder for where mode changes could be pushed.
    }
}
