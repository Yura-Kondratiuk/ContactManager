package com.example.resume.viewmodel

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.resume.R
import com.example.resume.adapter.items.SelectImagePathItem
import com.example.resume.utils.SelectImagePathEnum
import com.example.resume.utils.default

class DescriptionViewModel : ViewModel() {

    var currentDrawable: Drawable? = null

    val isEditableUpdate = MutableLiveData<Boolean>().default(false)

    fun getDrawableFromUri(activity: Activity, uri: Uri): Drawable {
        val inputStream = activity.contentResolver.openInputStream(uri)
        val drawable = Drawable.createFromStream(inputStream, uri.toString())
        currentDrawable = drawable
        return drawable
    }

    fun getDrawableFromBitmap(activity: Activity, intent: Intent?): Drawable {
        val bitmap = intent?.extras?.get("data") as Bitmap
        val drawable = BitmapDrawable(activity.resources, bitmap)
        currentDrawable = drawable
        return drawable
    }

    fun getSelectImagePathItems(activity: Activity): List<SelectImagePathItem> {
        val list = mutableListOf<SelectImagePathItem>()
        list.add(SelectImagePathItem(SelectImagePathEnum.GALLERY, activity.getDrawable(R.drawable.ic_gallery), "Gallery"))
        list.add(SelectImagePathItem(SelectImagePathEnum.CAMERA, activity.getDrawable(R.drawable.ic_camera), "Camera"))
        list.add(SelectImagePathItem(SelectImagePathEnum.FILES, activity.getDrawable(R.drawable.ic_document), "Files"))
        return list
    }
}