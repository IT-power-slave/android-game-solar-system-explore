package com.solarsystem.explorers.ui

import android.webkit.JavascriptInterface

class GameBridge(
    private val onPlanetClick: (String) -> Unit,
    private val onPlanetFocus: (String) -> Unit,
    private val onSceneLoaded: () -> Unit
) {
    @JavascriptInterface
    fun onPlanetClick(id: String) {
        onPlanetClick.invoke(id)
    }

    @JavascriptInterface
    fun onPlanetFocus(id: String) {
        onPlanetFocus.invoke(id)
    }

    @JavascriptInterface
    fun onSceneLoaded() {
        onSceneLoaded.invoke()
    }
}
