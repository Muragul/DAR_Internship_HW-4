package com.example.tictactoe.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.Adapter
import com.example.tictactoe.R
import com.example.tictactoe.model.RecordsData

class RecordsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_records, container, false)
        recyclerView = rootView.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Adapter()
        recyclerView.adapter = adapter
        adapter.submitList(RecordsData.getRecordsList())

        rootView.findViewById<Button>(R.id.back_to_main_menu).setOnClickListener {
            findNavController().navigate(RecordsFragmentDirections.actionReturnToStart())
        }
        return rootView
    }

}