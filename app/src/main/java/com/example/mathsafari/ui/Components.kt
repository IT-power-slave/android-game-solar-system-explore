package com.example.mathsafari.ui

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathsafari.data.Badge
import com.example.mathsafari.data.Planet
import com.example.mathsafari.data.PlanetRepository

@Composable
fun HUDView(
    pts: Int,
    levelName: String,
    levelIcon: String,
    progress: Float,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF040E26).copy(alpha = 0.8f)),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, Color(0xFF64B4FF).copy(alpha = 0.25f))
        ) {
            Column(modifier = Modifier.padding(8.dp, 4.dp)) {
                Text(
                    text = "$levelIcon $levelName",
                    color = Color(0xFFFFD700),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(6.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(progress)
                            .background(Brush.horizontalGradient(listOf(Color(0xFF4FC3F7), Color(0xFFFFD700))))
                    )
                }
            }
        }

        Button(
            onClick = onMenuClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.7f)),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Text("🏠 Menu", color = Color.White)
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF040E26).copy(alpha = 0.8f)),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp, Color(0xFF64B4FF).copy(alpha = 0.25f))
        ) {
            Row(modifier = Modifier.padding(8.dp, 4.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("⭐", fontSize = 16.sp)
                Spacer(Modifier.width(4.dp))
                Text(text = "$pts", color = Color(0xFFFFD700), fontWeight = FontWeight.Black, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun MainMenuView(
    totalPts: Int,
    onStartExplore: () -> Unit,
    onStartQuiz: () -> Unit,
    onStartLineup: () -> Unit,
    onShowBadges: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000510).copy(alpha = 0.82f)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "🚀 Odkrywcy\nUkładu Słonecznego",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 44.sp,
                color = Color(0xFFFFD700)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Poznaj planety i zostań Mistrzem Kosmosu!",
                color = Color(0xFFB4DCFF).copy(alpha = 0.75f),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(32.dp))
            
            MenuButton("🔭 Eksploruj Układ", Color(0xFF1565C0), onStartExplore)
            Spacer(Modifier.height(12.dp))
            MenuButton("🎮 Quiz Planetarny", Color(0xFFFF8C00), onStartQuiz)
//            Spacer(Modifier.height(12.dp))
//            MenuButton("🪐 Ucz się", Color(0xFF0D7377), onStartLineup)
            Spacer(Modifier.height(12.dp))
            MenuButton("🏆 Moje Odznaki", Color(0xFF6A1B9A), onShowBadges)
            
            Spacer(Modifier.height(24.dp))
            Text(
                text = "Twoje punkty: $totalPts ⭐",
                color = Color(0xFFFFD700),
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun MenuButton(text: String, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.width(280.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(text = text, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
    }
}

@Composable
fun InfoPanelView(planet: Planet, onClose: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .heightIn(max = 500.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF040E26).copy(alpha = 0.95f)),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFF64B4FF).copy(alpha = 0.25f))
    ) {
        Box(modifier = Modifier.padding(20.dp)) {
            IconButton(
                onClick = onClose,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
            }
            
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = planet.emoji, fontSize = 44.sp)
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text(
                            text = planet.name,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFFFFD700)
                        )
                        Text(text = planet.number, color = Color(0xFFB4DCFF).copy(alpha = 0.55f), fontSize = 12.sp)
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text(text = planet.desc, color = Color.White.copy(alpha = 0.9f), lineHeight = 20.sp)
                Spacer(Modifier.height(16.dp))
                Text(text = "📚 CIEKAWOSTKI", color = Color(0xFF4FC3F7), fontWeight = FontWeight.ExtraBold, fontSize = 12.sp)
                Spacer(Modifier.height(8.dp))
                planet.facts.forEach { fact ->
                    FactItem(fact)
                }
                Spacer(Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD700).copy(alpha = 0.1f)),
                    border = BorderStroke(1.dp, Color(0xFFFFA500).copy(alpha = 0.35f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("🎉 WOW-FAKT!", color = Color(0xFFFFA500), fontWeight = FontWeight.Black, fontSize = 10.sp)
                        Text(text = planet.wow, color = Color(0xFFFFD700), fontSize = 14.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FactItem(text: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Box(modifier = Modifier.width(3.dp).height(24.dp).background(Color(0xFF4FC3F7)))
        Spacer(Modifier.width(8.dp))
        Text(text = text, color = Color(0xFFC8DCFF), fontSize = 14.sp)
    }
}

@Composable
fun BadgesView(earnedBadges: Set<String>, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000510).copy(alpha = 0.96f))
            .padding(16.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "🏆 Moje Odznaki",
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFFFFD700)
            )
            Text(
                text = "Zdobyłeś/aś ${earnedBadges.size} z ${PlanetRepository.badges.size} odznak!",
                color = Color(0xFFB4DCFF).copy(alpha = 0.65f)
            )
            Spacer(Modifier.height(24.dp))
            
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(PlanetRepository.badges) { badge ->
                    BadgeCard(badge, earnedBadges.contains(badge.id))
                }
            }
            
            Button(onClick = onBack, modifier = Modifier.padding(16.dp)) {
                Text("🏠 Powrót do Menu")
            }
        }
    }
}

@Composable
fun BadgeCard(badge: Badge, isEarned: Boolean) {
    val borderColor = if (isEarned) Color(0xFFFFD700).copy(alpha = 0.45f) else Color(0xFF64B4FF).copy(alpha = 0.15f)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isEarned) Color(0xFFFFD700).copy(alpha = 0.08f) else Color.White.copy(alpha = 0.04f)
        ),
        border = BorderStroke(2.dp, borderColor),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = badge.icon,
                fontSize = 36.sp,
                modifier = Modifier.alpha(if (isEarned) 1f else 0.25f)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = badge.name,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                color = if (isEarned) Color.White else Color.White.copy(alpha = 0.35f)
            )
            Text(
                text = if (isEarned) badge.desc else "???",
                fontSize = 11.sp,
                color = Color(0xFFB4DCFF).copy(alpha = 0.55f),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

@Composable
fun QuizView(
    questions: List<com.example.mathsafari.data.Question>,
    currentIndex: Int,
    score: Int,
    streak: Int,
    onAnswer: (Boolean, Double) -> Unit,
    onFinish: () -> Unit
) {
    if (questions.isEmpty()) return

    val currentQuestion = questions[currentIndex]
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var showFeedback by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableFloatStateOf(30f) }
    val startTime = remember { System.currentTimeMillis() }

    LaunchedEffect(currentIndex) {
        selectedIndex = null
        showFeedback = false
        timeLeft = 30f
    }

    LaunchedEffect(showFeedback) {
        if (!showFeedback) {
            while (timeLeft > 0) {
                kotlinx.coroutines.delay(100)
                timeLeft -= 0.1f
            }
            if (selectedIndex == null) {
                // Timeout: Show feedback and notify VM
                showFeedback = true
                onAnswer(false, 30.0)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000510).copy(alpha = 0.93f))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.widthIn(max = 600.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pytanie ${currentIndex + 1} z ${questions.size}",
                    color = Color(0xFFB4DCFF).copy(alpha = 0.8f),
                    fontWeight = FontWeight.Bold
                )
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD700).copy(alpha = 0.12f)),
                    border = BorderStroke(1.dp, Color(0xFFFFD700).copy(alpha = 0.4f)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = "⭐ $score/${questions.size}",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        color = Color(0xFFFFD700),
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            Spacer(Modifier.height(14.dp))
            LinearProgressIndicator(
                progress = { timeLeft / 30f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(9.dp)
                    .clip(RoundedCornerShape(5.dp)),
                color = if (timeLeft < 10) Color.Red else Color(0xFF4FC3F7),
                trackColor = Color.White.copy(alpha = 0.12f)
            )
            Spacer(Modifier.height(26.dp))
            Text(
                text = currentQuestion.q,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
            Spacer(Modifier.height(30.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(currentQuestion.opts.size) { index ->
                    val option = currentQuestion.opts[index]
                    val isCorrect = index == currentQuestion.ans
                    val isSelected = selectedIndex == index
                    
                    val color = when {
                        showFeedback && isCorrect -> Color(0xFF66BB6A).copy(alpha = 0.28f)
                        showFeedback && isSelected && !isCorrect -> Color(0xFFEF5350).copy(alpha = 0.28f)
                        else -> Color.White.copy(alpha = 0.05f)
                    }
                    val borderColor = when {
                        showFeedback && isCorrect -> Color(0xFF66BB6A)
                        showFeedback && isSelected && !isCorrect -> Color(0xFFEF5350)
                        else -> Color(0xFF64B4FF).copy(alpha = 0.25f)
                    }

                    Card(
                        modifier = Modifier
                            .clickable(enabled = !showFeedback) {
                                selectedIndex = index
                                showFeedback = true
                                val elapsed = (System.currentTimeMillis() - startTime) / 1000.0
                                onAnswer(isCorrect, elapsed)
                            },
                        colors = CardDefaults.cardColors(containerColor = color),
                        border = BorderStroke(2.dp, borderColor),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Box(modifier = Modifier.padding(18.dp, 14.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = option,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(18.dp))
            if (showFeedback) {
                Text(
                    text = if (selectedIndex == currentQuestion.ans) "✅ Brawo!" else "❌ Nie tym razem…",
                    color = if (selectedIndex == currentQuestion.ans) Color(0xFF66BB6A) else Color(0xFFEF5350),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = currentQuestion.exp,
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 14.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            if (streak >= 2) {
                Text(
                    text = "🔥 Seria: $streak!",
                    color = Color(0xFFFFD700),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 6.dp)
                )
            }
        }
    }
}

@Composable
fun QuizResultsView(
    score: Int,
    total: Int,
    earnedPts: Int,
    onRetry: () -> Unit,
    onMenu: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000510).copy(alpha = 0.96f)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Wyniki Quizu!",
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFFFFD700)
            )
            Text(
                text = when {
                    score >= 9 -> "⭐⭐⭐ Mistrz Kosmosu!"
                    score >= 7 -> "⭐⭐ Świetnie!"
                    score >= 5 -> "⭐ Nieźle!"
                    else -> "😅 Ćwicz dalej!"
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(Modifier.height(16.dp))
            Text(text = "$score", fontSize = 100.sp, fontWeight = FontWeight.Black, color = Color.White)
            Text(text = "pytań poprawnie na $total", color = Color(0xFFB4DCFF).copy(alpha = 0.65f))
            Spacer(Modifier.height(18.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD700).copy(alpha = 0.12f)),
                border = BorderStroke(1.dp, Color(0xFFFFD700).copy(alpha = 0.4f)),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "+$earnedPts punktów zdobytych!",
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 11.dp),
                    color = Color(0xFFFFD700),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = onRetry, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700))) {
                    Text("🔄 Zagraj ponownie", color = Color.Black, fontWeight = FontWeight.Bold)
                }
                Button(onClick = onMenu, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1565C0))) {
                    Text("🏠 Menu", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
