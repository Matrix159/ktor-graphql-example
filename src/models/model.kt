package com.matrix159.graphqlexample.models

// 1
enum class Position {
    GK, // Goalkeeper
    DEF, // Defender
    MID, // Midfielder
    FRW  // Forward
}

// 2
data class Player(var uid: String, var name: String, var team: String, var position: Position)

// 3
data class PlayerInput(val name: String, val team: String, val position: Position)
