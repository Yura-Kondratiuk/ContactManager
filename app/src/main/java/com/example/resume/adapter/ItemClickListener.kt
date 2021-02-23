package com.example.resume.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.resume.adapter.items.SelectImagePathItem
import com.example.resume.adapter.items.UserItem

interface ItemClickListener {

    fun onItemClicked(holder: ViewHolder?, userItem: UserItem?, pos: Int) {}

    fun onLongClicked(holder: ViewHolder?, userItem: UserItem?, pos: Int) {}

    fun onSelectImagePathItemClicked(
        holder: ViewHolder?,
        selectImagePathItem: SelectImagePathItem?,
        pos: Int
    ) {}
}