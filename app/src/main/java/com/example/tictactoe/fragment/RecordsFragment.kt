package com.example.tictactoe.fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tictactoe.Adapter
import com.example.tictactoe.databinding.FragmentRecordsBinding
import com.example.tictactoe.model.RecordsRepository

class RecordsFragment : BindingFragment<FragmentRecordsBinding>(FragmentRecordsBinding::inflate) {
    private lateinit var adapter: Adapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = Adapter()
            recyclerView.adapter = adapter
            adapter.submitList(RecordsRepository.getRecordsList())

            backToMainMenu.setOnClickListener {
                findNavController().navigate(RecordsFragmentDirections.actionReturnToStart())
            }
        }
    }
}