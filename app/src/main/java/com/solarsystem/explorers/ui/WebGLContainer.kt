package com.solarsystem.explorers.ui

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebGLContainer(
    modifier: Modifier = Modifier,
    mode: GameMode,
    onWebViewReady: (WebView) -> Unit,
    bridge: GameBridge
) {
    var webViewInstance by remember { mutableStateOf<WebView?>(null) }
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
                webViewInstance = this
                onWebViewReady(this)
            }
        },
        update = { }
    )

    // Automatically sync mode changes to the JS engine
    LaunchedEffect(mode) {
        val jsMode = when(mode) {
            GameMode.LINEUP -> "lineup"
            else -> "explore"
        }
        webViewInstance?.evaluateJavascript("setMode('$jsMode')", null)
    }
}
