package com.example.resume.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.resume.adapter.items.SelectImagePathItem


class SelectImagePathAdapter(
    private val list: List<SelectImagePathItem>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<SelectImagePathViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectImagePathViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SelectImagePathViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SelectImagePathViewHolder, position: Int) {
        val selectImagePathItem: SelectImagePathItem = list[position]
        holder.bind(selectImagePathItem)
        holder.itemView.setOnClickListener {
            itemClickListener.onSelectImagePathItemClicked(holder, selectImagePathItem, position)
        }
    }

    override fun getItemCount(): Int = list.size
}