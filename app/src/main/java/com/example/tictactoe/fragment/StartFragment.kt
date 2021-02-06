package com.example.tictactoe.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.FragmentStartBinding

class StartFragment : BindingFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            buttonNewGame.setOnClickListener {
                findNavController().navigate(StartFragmentDirections.actionToRegisterPage())
            }
            buttonViewRecords.setOnClickListener {
                findNavController().navigate(StartFragmentDirections.actionToRecordsPage())
            }
        }
    }

}