package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class RegisterFragment : Fragment() {

    private lateinit var firstUser: String
    private lateinit var secondUser: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_register, container, false)
        rootView.findViewById<Button>(R.id.button_start_game).setOnClickListener {
            firstUser = rootView.findViewById<EditText>(R.id.first_user).text.toString()
            secondUser = rootView.findViewById<EditText>(R.id.second_user).text.toString()
            if (firstUser != "" && secondUser != "")
                findNavController().navigate(
                    RegisterFragmentDirections.actionToGame(
                        firstUser,
                        secondUser
                    )
                )
            else
                Toast.makeText(context, "Fill out the data", Toast.LENGTH_SHORT).show()
        }
        return rootView
    }
}