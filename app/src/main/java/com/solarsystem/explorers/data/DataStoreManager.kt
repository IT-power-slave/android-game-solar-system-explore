package com.solarsystem.explorers.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    private val PTS_KEY = intPreferencesKey("sol_pts")
    private val BADGES_KEY = stringSetPreferencesKey("sol_badges")
    private val VISITED_KEY = stringSetPreferencesKey("sol_visited")
    private val QUIZ_N_KEY = intPreferencesKey("sol_quiz_n")

    val pts: Flow<Int> = context.dataStore.data.map { it[PTS_KEY] ?: 0 }
    val badges: Flow<Set<String>> = context.dataStore.data.map { it[BADGES_KEY] ?: emptySet() }
    val visited: Flow<Set<String>> = context.dataStore.data.map { it[VISITED_KEY] ?: emptySet() }
    val quizN: Flow<Int> = context.dataStore.data.map { it[QUIZ_N_KEY] ?: 0 }

    suspend fun savePts(pts: Int) {
        context.dataStore.edit { it[PTS_KEY] = pts }
    }

    suspend fun saveBadges(badges: Set<String>) {
        context.dataStore.edit { it[BADGES_KEY] = badges }
    }

    suspend fun saveVisited(visited: Set<String>) {
        context.dataStore.edit { it[VISITED_KEY] = visited }
    }

    suspend fun incrementQuizN() {
        context.dataStore.edit {
            val current = it[QUIZ_N_KEY] ?: 0
            it[QUIZ_N_KEY] = current + 1
        }
    }
}
