package com.mathheroes.safari.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mathheroes.safari.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class GameMode { MENU, EXPLORE, QUIZ, LINEUP, BADGES }

class GameViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    private val _mode = MutableStateFlow(GameMode.MENU)
    val mode: StateFlow<GameMode> = _mode.asStateFlow()

    private val _selectedPlanet = MutableStateFlow<Planet?>(null)
    val selectedPlanet: StateFlow<Planet?> = _selectedPlanet.asStateFlow()

    // Persistent state
    val pts = dataStoreManager.pts.stateIn(viewModelScope, SharingStarted.Eagerly, 0)
    val badges = dataStoreManager.badges.stateIn(viewModelScope, SharingStarted.Eagerly, emptySet())
    val visited = dataStoreManager.visited.stateIn(viewModelScope, SharingStarted.Eagerly, emptySet())
    val quizN = dataStoreManager.quizN.stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    // Quiz State
    private val _quizQuestions = MutableStateFlow<List<Question>>(emptyList())
    val quizQuestions: StateFlow<List<Question>> = _quizQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _quizScore = MutableStateFlow(0)
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    private val _quizFinished = MutableStateFlow(false)
    val quizFinished: StateFlow<Boolean> = _quizFinished.asStateFlow()

    private val _streak = MutableStateFlow(0)
    val streak: StateFlow<Int> = _streak.asStateFlow()

    private val _sessionEarnedPts = MutableStateFlow(0)
    val sessionEarnedPts: StateFlow<Int> = _sessionEarnedPts.asStateFlow()

    fun setMode(newMode: GameMode) {
        _mode.value = newMode
        if (newMode == GameMode.QUIZ) {
            startQuiz()
        }
    }

    fun selectPlanet(planet: Planet?) {
        _selectedPlanet.value = planet
        if (planet != null) {
            markVisited(planet.id)
        }
    }

    private fun markVisited(id: String) {
        val currentVisited = visited.value
        if (!currentVisited.contains(id)) {
            val newVisited = currentVisited + id
            viewModelScope.launch {
                dataStoreManager.saveVisited(newVisited)
                addPts(5)
                if (newVisited.size == PlanetRepository.planets.size) {
                    awardBadge("explorer")
                }
            }
        }
    }

    fun addPts(amount: Int) {
        viewModelScope.launch {
            val newPts = pts.value + amount
            dataStoreManager.savePts(newPts)
            checkPtsBadges(newPts)
        }
    }

    private fun checkPtsBadges(currentPts: Int) {
        if (currentPts >= 100) awardBadge("pts100")
        if (currentPts >= 300) awardBadge("pts300")
        if (currentPts >= 500) awardBadge("pts500")
    }

    private fun awardBadge(id: String) {
        val currentBadges = badges.value
        if (!currentBadges.contains(id)) {
            viewModelScope.launch {
                dataStoreManager.saveBadges(currentBadges + id)
            }
        }
    }

    private fun startQuiz() {
        _quizQuestions.value = PlanetRepository.questions.shuffled().take(10)
        _currentQuestionIndex.value = 0
        _quizScore.value = 0
        _streak.value = 0
        _sessionEarnedPts.value = 0
        _quizFinished.value = false
        viewModelScope.launch {
            dataStoreManager.incrementQuizN()
            if (quizN.value + 1 >= 3) awardBadge("quiz3")
        }
    }

    fun answerQuestion(correct: Boolean, elapsedSeconds: Double) {
        viewModelScope.launch {
            if (correct) {
                _quizScore.value++
                _streak.value++
                var pointsEarned = 10
                if (elapsedSeconds < 5) {
                    pointsEarned += 5
                    awardBadge("quick")
                }
                if (_streak.value >= 2) pointsEarned += _streak.value * 2
                
                _sessionEarnedPts.value += pointsEarned
                
                if (_streak.value >= 3) awardBadge("streak3")
                if (_streak.value >= 5) awardBadge("streak5")
            } else {
                _streak.value = 0
            }

            // Expand the time to see the correct answer to 5 seconds
            kotlinx.coroutines.delay(5000)

            if (_mode.value != GameMode.QUIZ) return@launch

            if (_currentQuestionIndex.value + 1 < _quizQuestions.value.size) {
                _currentQuestionIndex.value++
            } else {
                finishQuiz()
            }
        }
    }

    private fun finishQuiz() {
        _quizFinished.value = true
        addPts(_sessionEarnedPts.value)
        awardBadge("first_quiz")
        if (_quizScore.value == 10) awardBadge("perfect")
    }
    
    fun resetQuiz() {
        startQuiz()
    }

    fun getLevel(): Level {
        return PlanetRepository.levels.lastOrNull { pts.value >= it.min } ?: PlanetRepository.levels.first()
    }

    fun getLevelProgress(): Float {
        val currentLevel = getLevel()
        val nextLevel = PlanetRepository.levels.getOrNull(PlanetRepository.levels.indexOf(currentLevel) + 1)
        return if (nextLevel != null) {
            (pts.value - currentLevel.min).toFloat() / (nextLevel.min - currentLevel.min)
        } else {
            1.0f
        }
    }
}
