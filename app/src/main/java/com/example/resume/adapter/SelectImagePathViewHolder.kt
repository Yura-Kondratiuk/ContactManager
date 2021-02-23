package com.example.resume.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resume.R
import com.example.resume.adapter.items.SelectImagePathItem
import kotlinx.android.synthetic.main.item_image_path.view.*

class SelectImagePathViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_image_path, parent, false)) {

    private lateinit var selectImagePathItem: SelectImagePathItem

    fun bind(selectImagePathItem: SelectImagePathItem) {
        this.selectImagePathItem = selectImagePathItem
        itemView.ivImage.background = selectImagePathItem.image
        itemView.tvName.text = selectImagePathItem.name
    }
}