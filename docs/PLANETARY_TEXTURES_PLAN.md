# Plan: Advanced Procedural Planetary Textures

The goal is to replace the current simple `makeTex` canvas drawing with a more sophisticated system that uses Fractal Brownian Motion (fBm) and multi-layered noise to simulate realistic planetary surfaces.

## 1. Texture Generation Algorithms

### A. Fractal Noise Engine
- Replace `Math.random()` circles with a deterministic noise generator.
- Use multiple octaves of Simplex/Perlin noise to create detailed terrain.
- Implement **Fractal Brownian Motion (fBm)** for realistic rough edges (mountains/craters).

### B. Planet-Specific Logic
- **Mercury/Mars**: High-contrast noise for craters + "Ray" patterns (ejection lines).
- **Earth**: 
    - Layer 0: Deep blue oceans.
    - Layer 1: Green/Brown continents based on height-map noise.
    - Layer 2: Dynamic cloud layer (semi-transparent).
- **Jupiter/Saturn**: 
    - Use sine-wave distorted noise to create "bands".
    - Add localized vortices (Great Red Spot) using rotational displacement.
- **Uranus/Neptune**: Subsurface scattering effect (using Fresnel gradients) + soft noise for frozen ammonia clouds.

## 2. Technical Implementation Steps

1.  **Refactor `makeTex`**: Create a specialized canvas drawing engine with a small internal noise library.
2.  **Atmospheric Shaders**: Introduce a simple Fresnel-based atmosphere for planets with thick air (Earth, Venus, Gas Giants).
3.  **Normal Mapping (Optional Simulation)**: Use luminance gradients to fake depth on rocky planets.

## 3. Realization Phase
- I will first implement a lightweight noise function in `game_engine.js`.
- Then, I will update each planet's drawing routine in `makeTex`.
- Finally, I will add the atmospheric "glow" effect to the planet meshes.
