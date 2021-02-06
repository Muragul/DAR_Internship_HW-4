package com.example.tictactoe

import com.example.tictactoe.model.Position
import com.example.tictactoe.model.RecordsRepository
import com.example.tictactoe.model.WinningLine

class GameManager(private val firstPlayer: String, private val secondPlayer: String) {

    var currentPlayer = 1

    val currentPlayerMark: Int
        get() {
            return if (currentPlayer == 1) R.drawable.ic_x else R.drawable.ic_o
        }

    private var state = arrayOf(
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )

    fun makeMove(position: Position): WinningLine? {
        state[position.row][position.column] = currentPlayer
        val winningLine = hasGameEnded()

        if (winningLine == null) {
            currentPlayer = 3 - currentPlayer
        } else {
            when (currentPlayer) {
                1 -> {
                    RecordsRepository.updateUserRecord(firstPlayer, true)
                    RecordsRepository.updateUserRecord(secondPlayer, false)
                }
                2 -> {
                    RecordsRepository.updateUserRecord(secondPlayer, true)
                    RecordsRepository.updateUserRecord(firstPlayer, false)
                }
            }
        }
        return winningLine
    }

    fun reset() {
        state = arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0),
            intArrayOf(0, 0, 0)
        )
        currentPlayer = 1
    }

    private fun hasGameEnded(): WinningLine? {
        if (state[0][0] == currentPlayer && state[0][1] == currentPlayer && state[0][2] == currentPlayer) {
            return WinningLine.ROW_0
        } else if (state[1][0] == currentPlayer && state[1][1] == currentPlayer && state[1][2] == currentPlayer) {
            return WinningLine.ROW_1
        } else if (state[2][0] == currentPlayer && state[2][1] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.ROW_2
        } else if (state[0][0] == currentPlayer && state[1][0] == currentPlayer && state[2][0] == currentPlayer) {
            return WinningLine.COLUMN_0
        } else if (state[0][1] == currentPlayer && state[1][1] == currentPlayer && state[2][1] == currentPlayer) {
            return WinningLine.COLUMN_1
        } else if (state[0][2] == currentPlayer && state[1][2] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.COLUMN_2
        } else if (state[0][0] == currentPlayer && state[1][1] == currentPlayer && state[2][2] == currentPlayer) {
            return WinningLine.DIAGONAL_LEFT
        } else if (state[0][2] == currentPlayer && state[1][1] == currentPlayer && state[2][0] == currentPlayer) {
            return WinningLine.DIAGONAL_RIGHT
        }
        return null
    }
}