package com.example.tictactoe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.model.Record

class Adapter : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var list = listOf<Record>()

    fun submitList(newList: List<Record>?) {
        list = newList ?: listOf()
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var userName = view.findViewById<TextView>(R.id.username)
        private var loseCnt = view.findViewById<TextView>(R.id.lose_cnt)
        private var winCnt = view.findViewById<TextView>(R.id.win_cnt)

        fun bind(item: Record) {
            userName.text = item.name
            loseCnt.text = item.loseCount.toString()
            winCnt.text = item.winCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}