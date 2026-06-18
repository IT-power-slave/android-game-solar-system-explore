package com.solarsystem.explorers.data

data class Planet(
    val id: String,
    val name: String,
    val number: String,
    val emoji: String,
    val desc: String,
    val facts: List<String>,
    val wow: String
)

data class Question(
    val q: String,
    val opts: List<String>,
    val ans: Int,
    val exp: String
)

data class Badge(
    val id: String,
    val icon: String,
    val name: String,
    val desc: String
)

data class Level(
    val min: Int,
    val icon: String,
    val name: String
)
