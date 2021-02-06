package com.example.tictactoe.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.FragmentRegisterBinding

class RegisterFragment :
    BindingFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            buttonStartGame.setOnClickListener {
                val firstPlayer = firstUser.text.toString()
                val secondPlayer = secondUser.text.toString()
                if (firstPlayer != "" && secondPlayer != "" && firstPlayer != secondPlayer)
                    findNavController().navigate(
                        RegisterFragmentDirections.actionToGame(
                            firstPlayer,
                            secondPlayer
                        )
                    )
                else
                    Toast.makeText(context, "Fill out correct data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}