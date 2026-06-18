package com.example.mathsafari

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathsafari.data.DataStoreManager
import com.example.mathsafari.data.PlanetRepository
import com.example.mathsafari.ui.*

class MainActivity : ComponentActivity() {

    private val viewModel: GameViewModel by viewModels {
        GameViewModelFactory(DataStoreManager(applicationContext))
    }

    private var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mode by viewModel.mode.collectAsState()
            val pts by viewModel.pts.collectAsState()
            val badges by viewModel.badges.collectAsState()
            val selectedPlanet by viewModel.selectedPlanet.collectAsState()
            
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF000510)) {
                    var sceneLoaded by remember { mutableStateOf(false) }
                    
                    Box(modifier = Modifier.fillMaxSize()) {
                        
                        // 3D Layer
                        WebGLContainer(
                            modifier = Modifier.fillMaxSize(),
                            mode = mode,
                            onWebViewReady = { webView = it },
                            bridge = GameBridge(
                                onPlanetClick = { id ->
                                    val planet = PlanetRepository.planets.find { it.id == id }
                                    viewModel.selectPlanet(planet)
                                },
                                onPlanetFocus = { id ->
                                    if (mode != GameMode.LINEUP) {
                                        val planet = PlanetRepository.planets.find { it.id == id }
                                        viewModel.selectPlanet(planet)
                                    } else {
                                        viewModel.selectPlanet(null)
                                    }
                                },
                                onSceneLoaded = {
                                    sceneLoaded = true
                                }
                            )
                        )
                        
                        // Loading screen
                        if (!sceneLoaded) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0xFF000510)),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    CircularProgressIndicator(
                                        color = Color(0xFF4FC3F7),
                                        trackColor = Color.White.copy(alpha = 0.12f)
                                    )
                                    Spacer(Modifier.height(16.dp))
                                    Text(
                                        text = "Ładuję...",
                                        color = Color(0xFFB4DCFF),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }

                        // UI Layer
                        if (mode != GameMode.MENU && mode != GameMode.BADGES && mode != GameMode.QUIZ) {
                            HUDView(
                                pts = pts,
                                levelName = viewModel.getLevel().name,
                                levelIcon = viewModel.getLevel().icon,
                                progress = viewModel.getLevelProgress(),
                                onMenuClick = { 
                                    viewModel.setMode(GameMode.MENU)
                                }
                            )
                        }

                        when (mode) {
                            GameMode.MENU -> {
                                MainMenuView(
                                    totalPts = pts,
                                    onStartExplore = { 
                                        viewModel.setMode(GameMode.EXPLORE)
                                    },
                                    onStartQuiz = { viewModel.setMode(GameMode.QUIZ) },
                                    onStartLineup = { 
                                        viewModel.setMode(GameMode.LINEUP)
                                    },
                                    onShowBadges = { viewModel.setMode(GameMode.BADGES) }
                                )
                            }
                            GameMode.EXPLORE -> {
                                selectedPlanet?.let { planet ->
                                    Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                                        InfoPanelView(planet = planet, onClose = { viewModel.selectPlanet(null) })
                                    }
                                }
                            }
                            GameMode.LINEUP -> {
                                Column(
                                    modifier = Modifier.fillMaxSize().padding(bottom = 32.dp),
                                    verticalArrangement = Arrangement.Bottom,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                                        Button(onClick = { webView?.evaluateJavascript("panLineup(-1)", null) }) {
                                            Text("◀")
                                        }
                                        Button(onClick = { webView?.evaluateJavascript("panLineup(1)", null) }) {
                                            Text("▶")
                                        }
                                    }
                                    selectedPlanet?.let { planet ->
                                        Spacer(Modifier.height(16.dp))
                                        InfoPanelView(planet = planet, onClose = { viewModel.selectPlanet(null) })
                                    }
                                }
                            }
                            GameMode.QUIZ -> {
                                val quizFinished by viewModel.quizFinished.collectAsState()
                                val score by viewModel.quizScore.collectAsState()
                                val questions by viewModel.quizQuestions.collectAsState()

                                if (!quizFinished) {
                                    val curIdx by viewModel.currentQuestionIndex.collectAsState()
                                    val streak by viewModel.streak.collectAsState()
                                    
                                    QuizView(
                                        questions = questions,
                                        currentIndex = curIdx,
                                        score = score,
                                        streak = streak,
                                        onAnswer = { correct, elapsed -> viewModel.answerQuestion(correct, elapsed) },
                                        onFinish = { },
                                        onMenu = { viewModel.setMode(GameMode.MENU) }
                                    )
                                } else {
                                    val sessionEarnedPts by viewModel.sessionEarnedPts.collectAsState()
                                    
                                    QuizResultsView(
                                        score = score,
                                        total = questions.size,
                                        earnedPts = sessionEarnedPts,
                                        onRetry = { viewModel.resetQuiz() },
                                        onMenu = { viewModel.setMode(GameMode.MENU) }
                                    )
                                }
                            }
                            GameMode.BADGES -> {
                                BadgesView(earnedBadges = badges, onBack = { viewModel.setMode(GameMode.MENU) })
                            }
                        }
                    }
                }
            }
        }
    }
}
