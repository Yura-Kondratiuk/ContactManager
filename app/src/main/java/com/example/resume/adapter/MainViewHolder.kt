package com.example.resume.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.resume.R
import com.example.resume.adapter.items.UserItem


class MainViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_main, parent, false)) {

    private var tvText: TextView? = null

    private lateinit var userItem: UserItem

    init {
        val itemView = this.itemView.findViewById<View>(R.id.item_main)
        tvText = itemView.findViewById(R.id.tvText)

    }

    fun bind(userItem: UserItem) {
        this.userItem = userItem
        tvText?.text = userItem.name
    }
}