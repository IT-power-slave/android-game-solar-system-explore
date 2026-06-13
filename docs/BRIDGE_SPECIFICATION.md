# Technical Specification: Hybrid Bridge

This document defines the communication protocol between the **Native Application Layer** (Android/Kotlin) and the **3D Graphics Layer** (WebGL/Three.js).

## 1. Architecture
- **Host**: Android WebView.
- **Interface Name**: `GameBridge`.
- **Direction**: Bi-directional communication using `JavascriptInterface` (Native to JS) and `evaluateJavascript` (JS to Native).

## 2. Native to Web (Calls from Kotlin to JS)
Used to control the 3D scene from the native UI.

| Method | Parameters | Description |
| :--- | :--- | :--- |
| `setMode(mode)` | `String` ("explore", "lineup") | Switches the camera and interaction logic in Three.js. |
| `focusPlanet(id)` | `String` (e.g., "mars") | Animates the camera to focus on a specific planet. |
| `panLineup(direction)` | `Int` (-1, 1) | Navigates the linear lineup view. |

## 3. Web to Native (Calls from JS to Native)
Used to notify the native layer of user interactions in the 3D scene.

| Method | Parameters | Description |
| :--- | :--- | :--- |
| `onPlanetClick(id)` | `String` | Triggered when a planet is tapped. Native should open the Info Panel. |
| `onSceneLoaded()` | `None` | Notifies that WebGL has finished loading assets. Native should hide loading screen. |

## 4. Data Transfer Objects (JSON)
Planet data is shared as a standardized JSON schema:
```json
{
  "id": "string",
  "name": "string",
  "emoji": "string",
  "facts": ["string"],
  "wow": "string"
}
```
