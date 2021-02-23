package com.example.resume.adapter.items

import android.graphics.drawable.Drawable
import com.example.resume.utils.SelectImagePathEnum

data class SelectImagePathItem(
    var type: SelectImagePathEnum,
    var image: Drawable?,
    var name: String
)