package com.example.tictactoe.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tictactoe.GameManager
import com.example.tictactoe.databinding.FragmentGameBinding
import com.example.tictactoe.model.Position
import com.example.tictactoe.model.WinningLine

class GameFragment : BindingFragment<FragmentGameBinding>(FragmentGameBinding::inflate) {

    private val args: GameFragmentArgs by navArgs()
    private lateinit var gameManager: GameManager
    private lateinit var firstUser: String
    private lateinit var secondUser: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            firstUser = args.firstUser
            secondUser = args.secondUser
            gameManager = GameManager(firstUser, secondUser)
            buttonContinue.setOnClickListener {
                gameManager.reset()
                findNavController().navigate(GameFragmentDirections.actionToRecords())
            }
            moveTurnPlayerName.text = firstUser

            cell1.setOnClickListener { onBoxClicked(cell1, Position(0, 0)) }
            cell2.setOnClickListener { onBoxClicked(cell2, Position(0, 1)) }
            cell3.setOnClickListener { onBoxClicked(cell3, Position(0, 2)) }
            cell4.setOnClickListener { onBoxClicked(cell4, Position(1, 0)) }
            cell5.setOnClickListener { onBoxClicked(cell5, Position(1, 1)) }
            cell6.setOnClickListener { onBoxClicked(cell6, Position(1, 2)) }
            cell7.setOnClickListener { onBoxClicked(cell7, Position(2, 0)) }
            cell8.setOnClickListener { onBoxClicked(cell8, Position(2, 1)) }
            cell9.setOnClickListener { onBoxClicked(cell9, Position(2, 2)) }

        }
    }

    private fun onBoxClicked(box: ImageView, position: Position) {
        box.isEnabled = false
        box.setImageResource(gameManager.currentPlayerMark)
        val winningLine = gameManager.makeMove(position)
        if (winningLine != null) {
            disableBoxes()
            enableContinueButton()
            showWinnerLine(winningLine)
        }
        toggleCurrentPlayer()
    }

    private fun showWinnerLine(winningLine: WinningLine) {
        binding.run {
            if (winningLine == WinningLine.NOBODY)
                enableContinueButton()
            else {
                val boxList = when (winningLine) {
                    WinningLine.ROW_0 -> listOf(cell1, cell2, cell3)
                    WinningLine.ROW_1 -> listOf(cell4, cell5, cell6)
                    WinningLine.ROW_2 -> listOf(cell7, cell8, cell9)
                    WinningLine.COLUMN_0 -> listOf(cell1, cell4, cell7)
                    WinningLine.COLUMN_1 -> listOf(cell2, cell5, cell8)
                    WinningLine.COLUMN_2 -> listOf(cell3, cell6, cell9)
                    WinningLine.DIAGONAL_LEFT -> listOf(cell1, cell5, cell9)
                    WinningLine.DIAGONAL_RIGHT -> listOf(cell3, cell5, cell7)
                    WinningLine.NOBODY -> listOf()
                }
                boxList.forEach { box ->
                    box.setBackgroundColor(Color.CYAN)
                }
            }
        }
    }

    private fun disableBoxes() {
        binding.run {
            cell1.isEnabled = false
            cell2.isEnabled = false
            cell3.isEnabled = false
            cell4.isEnabled = false
            cell5.isEnabled = false
            cell6.isEnabled = false
            cell7.isEnabled = false
            cell8.isEnabled = false
            cell9.isEnabled = false
        }
    }

    private fun enableContinueButton() {
        binding.buttonContinue.visibility = View.VISIBLE
    }

    private fun toggleCurrentPlayer() {
        binding.run {
            if (gameManager.currentPlayer == 1)
                moveTurnPlayerName.text = firstUser
            else
                moveTurnPlayerName.text = secondUser
        }
    }

}