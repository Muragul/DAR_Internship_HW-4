package com.example.tictactoe.model

import java.util.*

data class Record(
    val name: String,
    var loseCount: Int,
    var winCount: Int
) {
    override fun equals(other: Any?): Boolean {
        if (other !is Record) return false
        return name == other.name
    }

    override fun hashCode(): Int {
        return Objects.hash(name)
    }

    override fun toString(): String {
        return name
    }
}