# Planet Rendering Engine: Technical Audit

## 1. Audit Checklist (Per Celestial Body)
For each body in the solar system, we will evaluate:
- **T (Texture)**: Does it use the High-Fidelity `Noise.fbm` generator? Are colors accurate?
- **R (Rendering)**: Does it use high-poly geometry (64+ segments)? Is lighting consistent?
- **V (Visibility)**: Is it rendered and visible on the first frame of "Explore" and "Lineup" modes?
- **L (Logic)**: Are its `userData` and `planetMeshes` index correctly mapped to the `PLANETS` array?

## 2. Global Logic Review
- **Timing Race Conditions**: Ensure `makeTex` (Canvas) and `THREE.WebGLRenderer` (GPU) are synced correctly to avoid black textures on startup.
- **Lineup Depth**: Verify `camZ` and `camY` offsets in Lineup mode don't cause clipping on larger bodies (Sun/Jupiter).
- **Shader Stability**: Ensure the Sun's `ShaderMaterial` updates correctly regardless of current mode.

## 3. Planet-by-Planet Status
| Body | T | R | V | L | Notes |
| :--- | :---: | :---: | :---: | :---: | :--- |
| **Sun** | ✅ | ✅ | ⚠️ | ⚠️ | Shader logic needs mode-independence. |
| **Mercury** | ✅ | ✅ | ⚠️ | ✅ | Needs better contrast in noise. |
| **Venus** | ✅ | ✅ | ⚠️ | ✅ | Texture can look flat. |
| **Earth** | ✅ | ✅ | ✅ | ✅ | Moon needs correct Lineup positioning. |
| **Mars** | ✅ | ✅ | ✅ | ✅ | Optimal. |
| **Jupiter** | ✅ | ✅ | ✅ | ✅ | Bands logic is stable. |
| **Saturn** | ✅ | ✅ | ✅ | ✅ | Rings need orientation check in Lineup. |
| **Uranus** | ✅ | ✅ | ✅ | ✅ | Tilt logic in Lineup check. |
| **Neptune** | ✅ | ✅ | ✅ | ✅ | Optimal. |

## 4. Remediation Plan
1.  **Refactor initialization**: Trigger a global texture re-generation 100ms after the WebGL context is ready.
2.  **Harmonize Sun/Planet Logic**: Merge `buildSun` and `buildPlanets` more cleanly.
3.  **Fix Lineup Rendering**: Ensure all 9 bodies (including Sun) are pre-rendered in a "hidden" state to avoid pop-in.
