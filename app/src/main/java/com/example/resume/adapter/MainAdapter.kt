package com.example.resume.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resume.adapter.items.UserItem


class MainAdapter(private val list: List<UserItem>, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val userItem: UserItem = list[position]
        holder.bind(userItem)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClicked(holder, userItem, position)
        }
        holder.itemView.setOnLongClickListener {
            itemClickListener.onLongClicked(holder, userItem, position)
            true
        }
    }

    override fun getItemCount(): Int = list.size
}