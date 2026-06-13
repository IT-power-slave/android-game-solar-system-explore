/* ════════════════════════════════════════════════════════
   DATA
════════════════════════════════════════════════════════ */
const PLANETS = [
  { id:'sun', name:'Słońce', color:0xFFC800, emissive:0xFFC800, radius:2.6, orbit:0, speed:0, spin:0.003 },
  { id:'mercury', name:'Merkury', color:0xA8A8A8, emissive:0x111111, radius:.38, orbit:5.5, speed:.024, spin:.004 },
  { id:'venus', name:'Wenus', color:0xE8CD9C, emissive:0x1e1000, radius:.95, orbit:8, speed:.0175, spin:.0015 },
  { id:'earth', name:'Ziemia', color:0x2E6EAB, emissive:0x001020, radius:1, orbit:11, speed:.01, spin:.01, hasMoon:true },
  { id:'mars', name:'Mars', color:0xC1440E, emissive:0x190000, radius:.53, orbit:14.5, speed:.0053, spin:.009 },
  { id:'jupiter', name:'Jowisz', color:0xC98C3A, emissive:0x150900, radius:2.5, orbit:20, speed:.00084, spin:.04 },
  { id:'saturn', name:'Saturn', color:0xE8D5A3, emissive:0x151000, radius:2.1, orbit:27, speed:.00034, spin:.038, hasRings:true },
  { id:'uranus', name:'Uran', color:0x7DECEC, emissive:0x001414, radius:1.6, orbit:33, speed:.00012, spin:.03, hasRings:true },
  { id:'neptune', name:'Neptun', color:0x3F54BA, emissive:0x00001a, radius:1.5, orbit:39, speed:.00006, spin:.032 },
];

/* ════════════════════════════════════════════════════════
   PROCEDURAL NOISE ENGINE
════════════════════════════════════════════════════════ */
const Noise = {
    hash: (x, y) => {
        let h = (x * 127.1 + y * 311.7);
        return (Math.sin(h) * 43758.5453123) % 1;
    },
    smooth: (x, y) => {
        const i = Math.floor(x), j = Math.floor(y);
        const f = x - i, g = y - j;
        const u = f * f * (3 - 2 * f), v = g * g * (3 - 2 * g);
        const a = Noise.hash(i, j), b = Noise.hash(i + 1, j);
        const c = Noise.hash(i, j + 1), d = Noise.hash(i + 1, j + 1);
        return a + (b - a) * u + (c - a) * v + (a - b - c + d) * u * v;
    },
    fbm: (x, y, octaves = 5) => {
        let val = 0, amp = 0.5, freq = 1;
        for (let i = 0; i < octaves; i++) {
            val += amp * Noise.smooth(x * freq, y * freq);
            freq *= 2.1; amp *= 0.5;
        }
        return val;
    }
};

/* ════════════════════════════════════════════════════════
   ADVANCED SHADERS (Realistic Sun/Lava)
════════════════════════════════════════════════════════ */
const SunShader = {
    uniforms: {
        time: { value: 0 },
        sunColor: { value: new THREE.Color(0xff8c00) },
        hotspotColor: { value: new THREE.Color(0xffff00) }
    },
    vertexShader: `
        varying vec2 vUv;
        varying vec3 vNormal;
        varying vec3 vViewPosition;
        void main() {
            vUv = uv;
            vNormal = normalize(normalMatrix * normal);
            vec4 mvPosition = modelViewMatrix * vec4(position, 1.0);
            vViewPosition = -mvPosition.xyz;
            gl_Position = projectionMatrix * mvPosition;
        }
    `,
    fragmentShader: `
        varying vec2 vUv;
        varying vec3 vNormal;
        varying vec3 vViewPosition;
        uniform float time;
        uniform vec3 sunColor;
        uniform vec3 hotspotColor;

        vec3 permute(vec3 x) { return mod(((x*34.0)+1.0)*x, 289.0); }
        float snoise(vec2 v) {
            const vec4 C = vec4(0.211324865405187, 0.366025403784439, -0.577350269189626, 0.024390243902439);
            vec2 i  = floor(v + dot(v, C.yy));
            vec2 x0 = v -   i + dot(i, C.xx);
            vec2 i1 = (x0.x > x0.y) ? vec2(1.0, 0.0) : vec2(0.0, 1.0);
            vec4 x12 = x0.xyxy + C.xxzz;
            x12.xy -= i1;
            i = mod(i, 289.0);
            vec3 p = permute( permute( i.y + vec3(0.0, i1.y, 1.0 )) + i.x + vec3(0.0, i1.x, 1.0 ));
            vec3 m = max(0.5 - vec3(dot(x0,x0), dot(x12.xy,x12.xy), dot(x12.zw,x12.zw)), 0.0);
            m = m*m ; m = m*m ;
            vec3 x = 2.0 * fract(p * C.www) - 1.0;
            vec3 h = abs(x) - 0.5;
            vec3 ox = floor(x + 0.5);
            vec3 a0 = x - ox;
            m *= 1.79284291400159 - 0.85373472095314 * ( a0*a0 + h*h );
            vec3 g;
            g.x  = a0.x  * x0.x  + h.x  * x0.y;
            g.yz = a0.yz * x12.xz + h.yz * x12.yw;
            return 130.0 * dot(m, g);
        }

        void main() {
            float noise1 = snoise(vUv * 4.0 + time * 0.1);
            float noise2 = snoise(vUv * 8.0 - time * 0.2);
            float finalNoise = (noise1 + noise2 * 0.5) * 0.5 + 0.5;
            vec3 baseColor = mix(sunColor, hotspotColor, pow(finalNoise, 3.0));
            float rim = 1.0 - max(dot(normalize(vNormal), normalize(vViewPosition)), 0.0);
            rim = pow(rim, 3.0);
            float explosion = smoothstep(0.7, 0.9, finalNoise);
            vec3 color = baseColor + (hotspotColor * explosion * 2.0) + (hotspotColor * rim);
            gl_FragColor = vec4(color, 1.0);
        }
    `
};

/* ════════════════════════════════════════════════════════
   THREE.JS ENGINE
════════════════════════════════════════════════════════ */
let scene, camera, renderer, controls, clock;
let sunMesh;
let sunObjects = [];
let orbitLines = [];
let planetMeshes = [];
let raycaster, mouse = new THREE.Vector2();
let hovered = null;
let mode = 'explore'; // 'explore' | 'lineup'

const LINEUP_X = [0, 15, 30, 45, 60, 85, 115, 145, 175];
const LINEUP_SCALE = [1.0, 1.8, 1.4, 1.4, 1.5, 1.4, 1.3, 1.3, 1.3];
let lineupStep = 0;
let lineupTargetX = LINEUP_X[0];
let lineupCurrentX = LINEUP_X[0];

function makeTex(p) {
  const c = document.createElement('canvas');
  c.width = 1024; c.height = 512;
  const g = c.getContext('2d');
  const w = c.width, h = c.height;

  if (p.id === 'mercury') {
    g.fillStyle = '#666'; g.fillRect(0,0,w,h);
    for(let x=0; x<w; x+=2) {
      for(let y=0; y<h; y+=2) {
        let n = Noise.fbm(x/40, y/40, 6);
        g.fillStyle = `rgb(${80+n*80},${80+n*80},${80+n*80})`;
        g.fillRect(x,y,2,2);
      }
    }
  } else if (p.id === 'venus') {
    const grd = g.createLinearGradient(0,0,0,h);
    grd.addColorStop(0, '#E8CD9C'); grd.addColorStop(0.5, '#B8860B'); grd.addColorStop(1, '#E8CD9C');
    g.fillStyle = grd; g.fillRect(0,0,w,h);
    for(let x=0; x<w; x+=4) {
      for(let y=0; y<h; y+=4) {
        let n = Noise.fbm(x/100, y/50, 3);
        g.fillStyle = `rgba(255,255,255,${n*0.2})`;
        g.fillRect(x,y,4,4);
      }
    }
  } else if (p.id === 'earth') {
    g.fillStyle = '#003366'; g.fillRect(0,0,w,h);
    for(let x=0; x<w; x+=2) {
      for(let y=0; y<h; y+=2) {
        let n = Noise.fbm(x/150, y/150, 7);
        if (n > 0.45) {
            let col = n > 0.6 ? `rgb(${100+n*50},${80+n*30},60)` : `rgb(${40+n*40},${100+n*60},40)`;
            g.fillStyle = col; g.fillRect(x,y,2,2);
        }
      }
    }
  } else if (p.id === 'mars') {
    g.fillStyle = '#8B4513'; g.fillRect(0,0,w,h);
    for(let x=0; x<w; x+=2) {
      for(let y=0; y<h; y+=2) {
        let n = Noise.fbm(x/100, y/100, 6);
        g.fillStyle = `rgb(${150+n*100},${50+n*30},${20+n*20})`;
        g.fillRect(x,y,2,2);
      }
    }
  } else if (p.id === 'jupiter') {
    for(let y=0; y<h; y+=4) {
        let band = Math.sin(y/20) * 0.5 + 0.5;
        let col = band > 0.5 ? '#C98C3A' : '#E8C07A';
        g.fillStyle = col; g.fillRect(0,y,w,4);
    }
  } else if (p.id === 'saturn') {
    for(let y=0; y<h; y+=4) {
        let n = Noise.smooth(0, y/30);
        g.fillStyle = `rgb(${200+n*55},${180+n*40},${130+n*30})`;
        g.fillRect(0,y,w,4);
    }
  } else {
    g.fillStyle = `#${p.color.toString(16).padStart(6,'0')}`;
    g.fillRect(0,0,w,h);
  }

  const tex = new THREE.CanvasTexture(c);
  tex.anisotropy = 4;
  tex.needsUpdate = true;
  return tex;
}

function init() {
  if (window.innerWidth === 0 || window.innerHeight === 0) {
      setTimeout(init, 50);
      return;
  }

  scene = new THREE.Scene();
  camera = new THREE.PerspectiveCamera(55, window.innerWidth/window.innerHeight, .1, 2000);
  camera.position.set(0, 32, 60);

  const canvas = document.getElementById('threeCanvas');
  renderer = new THREE.WebGLRenderer({ canvas, antialias:true });
  renderer.setSize(window.innerWidth, window.innerHeight);
  renderer.setPixelRatio(Math.min(window.devicePixelRatio,2));

  controls = new THREE.OrbitControls(camera, renderer.domElement);
  controls.enableDamping = true;
  controls.dampingFactor = .05;
  controls.minDistance = 8;
  controls.maxDistance = 250;
  controls.enablePan = false;

  scene.add(new THREE.AmbientLight(0x223355, .8));
  const sun_l = new THREE.PointLight(0xFFE8C0, 4.0, 500);
  scene.add(sun_l);

  clock = new THREE.Clock();
  raycaster = new THREE.Raycaster();

  buildStars();
  buildSun();
  buildPlanets();
  buildOrbits();

  window.addEventListener('resize', onWindowResize);
  canvas.addEventListener('mousemove', onMouseMove);
  canvas.addEventListener('click', onCanvasClick);
  canvas.addEventListener('touchstart', onTouchStart, {passive:true});

  renderer.compile(scene, camera);
  requestAnimationFrame(loop);

  if (window.GameBridge) {
      window.GameBridge.onSceneLoaded();
  }
}

function buildStars() {
  const geo = new THREE.BufferGeometry();
  const n=3500, pos=new Float32Array(n*3);
  for(let i=0;i<n;i++){
    pos[i*3]  =(Math.random()-.5)*1200;
    pos[i*3+1]=(Math.random()-.5)*1200;
    pos[i*3+2]=(Math.random()-.5)*1200;
  }
  geo.setAttribute('position', new THREE.BufferAttribute(pos,3));
  scene.add(new THREE.Points(geo, new THREE.PointsMaterial({color:0xFFFFFF,size:.35,transparent:true,opacity:.85})));
}

function buildSun() {
  const geo = new THREE.SphereGeometry(2.6,64,64);
  const mat = new THREE.ShaderMaterial({
      uniforms: THREE.UniformsUtils.clone(SunShader.uniforms),
      vertexShader: SunShader.vertexShader,
      fragmentShader: SunShader.fragmentShader
  });
  sunMesh = new THREE.Mesh(geo, mat);
  sunMesh.userData = { type:'planet', id: 'sun', data: PLANETS[0], angle: 0 }; // Initialize angle to avoid NaN
  scene.add(sunMesh);
  planetMeshes[0] = sunMesh;
  sunObjects.push(sunMesh);

  const glowGeo = new THREE.SphereGeometry(3.2, 64, 64);
  const glowMat = new THREE.MeshBasicMaterial({
      color: 0xff4500, transparent: true, opacity: 0.15, side: THREE.BackSide
  });
  const glow = new THREE.Mesh(glowGeo, glowMat);
  sunMesh.add(glow);
  sunObjects.push(glow);
}

function buildPlanets() {
  PLANETS.forEach((p,i)=>{
    if (p.id === 'sun') return;
    const geo = new THREE.SphereGeometry(p.radius,64,64);
    const mat = new THREE.MeshPhongMaterial({
      map: makeTex(p),
      emissive: new THREE.Color(p.emissive),
      emissiveIntensity:.35,
    });
    const mesh = new THREE.Mesh(geo,mat);
    mesh.userData = { type:'planet', id: p.id, data:p, angle: Math.random()*Math.PI*2 };
    scene.add(mesh);

    if (p.hasRings) {
      const ri=p.radius*1.32, ro=p.radius*(p.id==='saturn'?2.28:1.9);
      const rg=new THREE.RingGeometry(ri,ro,72);
      const rm=new THREE.MeshBasicMaterial({
        color: p.id==='saturn'?0xE0CC90:0x8899BB, transparent:true, opacity:p.id==='saturn'?.65:.35, side:THREE.DoubleSide,
      });
      const ring=new THREE.Mesh(rg,rm);
      ring.rotation.x=Math.PI/2.4;
      mesh.add(ring);
    }

    if (p.hasMoon) {
      const mm=new THREE.Mesh(new THREE.SphereGeometry(.27,24,24), new THREE.MeshPhongMaterial({color:0xCCCCCC}));
      // Assign data so flyTo and click logic have access to radius and id
      mm.userData={
          type:'moon', // Set back to moon to match loop animation logic
          id:'moon',
          data: { name:'Księżyc', radius: 0.27 },
          angle:Math.random()*Math.PI*2
      };
      mesh.add(mm);
    }
    planetMeshes[i] = mesh;
  });
}

function buildOrbits() {
  PLANETS.forEach(p=>{
    const pts=[];
    for(let i=0;i<=128;i++){
      const a=(i/128)*Math.PI*2;
      pts.push(new THREE.Vector3(Math.cos(a)*p.orbit,0,Math.sin(a)*p.orbit));
    }
    const line=new THREE.Line(new THREE.BufferGeometry().setFromPoints(pts), new THREE.LineBasicMaterial({color:0x334466,transparent:true,opacity:.35}));
    scene.add(line);
    orbitLines.push(line);
  });
}

function onWindowResize() {
  camera.aspect = window.innerWidth/window.innerHeight;
  camera.updateProjectionMatrix();
  renderer.setSize(window.innerWidth, window.innerHeight);
}

function onMouseMove(e) {
  mouse.x = (e.clientX/window.innerWidth)*2-1;
  mouse.y = -(e.clientY/window.innerHeight)*2+1;
}

function onTouchStart(e) {
  const t=e.touches[0];
  mouse.x=(t.clientX/window.innerWidth)*2-1;
  mouse.y=-(t.clientY/window.innerHeight)*2+1;
  onCanvasClick();
}

function onCanvasClick() {
  raycaster.setFromCamera(mouse, camera);
  const hits = raycaster.intersectObjects(planetMeshes, true); // Recursive check for moons
  if(!hits.length) return;
  const m = hits[0].object;
  if(m.userData.type==='planet' || m.userData.type==='moon'){
    if (window.GameBridge) {
        window.GameBridge.onPlanetClick(m.userData.id);
    }
    if (mode === 'explore') {
      flyTo(m);
    }
  }
}

function flyTo(mesh) {
  const tgt = new THREE.Vector3();
  mesh.getWorldPosition(tgt); // Use world position for flyTo
  const dist = mesh.userData.data.radius*7+6;
  const newPos = tgt.clone().add(new THREE.Vector3(1,.5,1).normalize().multiplyScalar(dist));
  const s0=camera.position.clone(), t0=controls.target.clone();
  let tk=0;
  (function fly(){
    tk+=.025; if(tk>1)tk=1;
    const e=tk<.5?2*tk*tk:-1+(4-2*tk)*tk;
    camera.position.lerpVectors(s0,newPos,e);
    controls.target.lerpVectors(t0,tgt,e);
    controls.update();
    if(tk<1) requestAnimationFrame(fly);
  })();
}

function loop() {
  requestAnimationFrame(loop);
  const t = clock.getElapsedTime();

  if (sunMesh && sunMesh.material && sunMesh.material.uniforms) {
      sunMesh.material.uniforms.time.value = t;
  }

  if (mode === 'lineup') {
    lineupCurrentX += (lineupTargetX - lineupCurrentX) * 0.08;
    const distance = 30;
    const angleRad = 25 * (Math.PI / 180);
    const camX = lineupCurrentX - distance * Math.cos(angleRad);
    const camY = distance * Math.sin(angleRad);
    const camZ = distance * 0.8;
    camera.position.set(camX, camY, camZ);
    camera.lookAt(lineupCurrentX, 0, 0);

    planetMeshes.forEach((mesh, i) => {
      mesh.position.set(LINEUP_X[i], 0, 0);
      mesh.rotation.y += 0.006;
      mesh.scale.setScalar(LINEUP_SCALE[i] * (i === lineupStep ? 1.3 : 1.0));
      if (mesh.material && t < 3.0) {
          mesh.material.needsUpdate = true;
          if (mesh.material.map) mesh.material.map.needsUpdate = true;
      }
    });
    renderer.render(scene, camera);
    return;
  }

  if(sunMesh) sunMesh.rotation.y += .003;

  planetMeshes.forEach(mesh=>{
    const ud=mesh.userData, p=ud.data;
    ud.angle += p.speed * .3;
    mesh.position.set(Math.cos(ud.angle)*p.orbit, 0, Math.sin(ud.angle)*p.orbit);
    mesh.rotation.y += p.spin;
    mesh.children.forEach(ch=>{
      if(ch.userData.type==='moon'){
        ch.userData.angle+=.022;
        ch.position.set(Math.cos(ch.userData.angle)*1.9,0,Math.sin(ch.userData.angle)*1.9);
      }
    });
  });

  if(mode==='explore') {
      raycaster.setFromCamera(mouse, camera);
      const hits = raycaster.intersectObjects(planetMeshes, true); // Recursive check for moons
      if(hits.length){
        const m = hits[0].object;
        if((m.userData.type === 'planet' || m.userData.type === 'moon') && m !== hovered){
          if(hovered) hovered.scale.setScalar(1);
          hovered = m;
          m.scale.setScalar(1.18);
          renderer.domElement.style.cursor='pointer';
        } else if (m.userData.type !== 'planet' && m.userData.type !== 'moon' && hovered) {
           hovered.scale.setScalar(1); hovered=null;
           renderer.domElement.style.cursor='default';
        }
      } else {
        if(hovered){ hovered.scale.setScalar(1); hovered=null; }
        renderer.domElement.style.cursor='default';
      }
  }

  controls.update();
  renderer.render(scene, camera);
}

window.setMode = function(newMode) {
    mode = newMode;
    if (mode === 'lineup') {
        sunObjects.forEach(m => m.visible = true);
        orbitLines.forEach(l => l.visible = false);
        controls.enabled = false;
        lineupStep = 0;
        lineupTargetX = LINEUP_X[0];
        lineupCurrentX = LINEUP_X[0];
    } else {
        sunObjects.forEach(m => m.visible = true);
        orbitLines.forEach(l => l.visible = true);
        controls.enabled = true;
        planetMeshes.forEach((mesh, i) => {
            mesh.scale.setScalar(1);
        });
        camera.position.set(0, 32, 60);
        controls.target.set(0, 0, 0);
    }
};

window.focusPlanet = function(id) {
    const mesh = planetMeshes.find(m => m.userData.id === id);
    if (mesh) flyTo(mesh);
};

window.panLineup = function(dir) {
    lineupStep = Math.max(0, Math.min(LINEUP_X.length - 1, lineupStep + dir));
    lineupTargetX = LINEUP_X[lineupStep];
    if (window.GameBridge) {
        window.GameBridge.onPlanetFocus(PLANETS[lineupStep].id);
    }
};

init();
