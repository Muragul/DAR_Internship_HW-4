package com.example.tictactoe.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tictactoe.GameManager
import com.example.tictactoe.R
import com.example.tictactoe.model.Position
import com.example.tictactoe.model.WinningLine

class GameFragment : Fragment() {
    private val args: GameFragmentArgs by navArgs()
    private lateinit var gameManager: GameManager
    private lateinit var firstUser: String
    private lateinit var secondUser: String
    private lateinit var one: ImageView
    private lateinit var two: ImageView
    private lateinit var three: ImageView
    private lateinit var four: ImageView
    private lateinit var five: ImageView
    private lateinit var six: ImageView
    private lateinit var seven: ImageView
    private lateinit var eight: ImageView
    private lateinit var nine: ImageView
    private lateinit var continueButton: Button
    private lateinit var currentMoveHint: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_game, container, false)
        firstUser = args.firstUser
        secondUser = args.secondUser
        gameManager = GameManager(firstUser, secondUser)

        rootView.findViewById<Button>(R.id.button_continue).setOnClickListener {
            gameManager.reset()
            findNavController().navigate(GameFragmentDirections.actionToRecords())
        }

        one = rootView.findViewById(R.id.cell_1)
        two = rootView.findViewById(R.id.cell_2)
        three = rootView.findViewById(R.id.cell_3)
        four = rootView.findViewById(R.id.cell_4)
        five = rootView.findViewById(R.id.cell_5)
        six = rootView.findViewById(R.id.cell_6)
        seven = rootView.findViewById(R.id.cell_7)
        eight = rootView.findViewById(R.id.cell_8)
        nine = rootView.findViewById(R.id.cell_9)
        continueButton = rootView.findViewById(R.id.button_continue)
        currentMoveHint = rootView.findViewById(R.id.move_turn_player_name)
        currentMoveHint.text = firstUser

        one.setOnClickListener { onBoxClicked(one, Position(0, 0)) }
        two.setOnClickListener { onBoxClicked(two, Position(0, 1)) }
        three.setOnClickListener { onBoxClicked(three, Position(0, 2)) }
        four.setOnClickListener { onBoxClicked(four, Position(1, 0)) }
        five.setOnClickListener { onBoxClicked(five, Position(1, 1)) }
        six.setOnClickListener { onBoxClicked(six, Position(1, 2)) }
        seven.setOnClickListener { onBoxClicked(seven, Position(2, 0)) }
        eight.setOnClickListener { onBoxClicked(eight, Position(2, 1)) }
        nine.setOnClickListener { onBoxClicked(nine, Position(2, 2)) }

        return rootView
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
        val boxList = when (winningLine) {
            WinningLine.ROW_0 -> listOf(one, two, three)
            WinningLine.ROW_1 -> listOf(four, five, six)
            WinningLine.ROW_2 -> listOf(seven, eight, nine)
            WinningLine.COLUMN_0 -> listOf(one, four, seven)
            WinningLine.COLUMN_1 -> listOf(two, five, eight)
            WinningLine.COLUMN_2 -> listOf(three, six, nine)
            WinningLine.DIAGONAL_LEFT -> listOf(one, five, nine)
            WinningLine.DIAGONAL_RIGHT -> listOf(three, five, seven)
        }
        boxList.forEach { box ->
            box.setBackgroundColor(Color.CYAN)
        }
    }

    private fun disableBoxes() {
        one.isEnabled = false
        two.isEnabled = false
        three.isEnabled = false
        four.isEnabled = false
        five.isEnabled = false
        six.isEnabled = false
        seven.isEnabled = false
        eight.isEnabled = false
        nine.isEnabled = false
    }

    private fun enableContinueButton() {
        continueButton.visibility = View.VISIBLE
    }

    private fun toggleCurrentPlayer() {
        if (gameManager.currentPlayer == 1)
            currentMoveHint.text = firstUser
        else
            currentMoveHint.text = secondUser
    }

}