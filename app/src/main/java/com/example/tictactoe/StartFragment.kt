package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_start, container, false)
        rootView.findViewById<Button>(R.id.button_new_game).setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionToRegisterPage())
        }
        rootView.findViewById<Button>(R.id.button_view_records).setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionToRecordsPage())
        }

        return rootView
    }


}