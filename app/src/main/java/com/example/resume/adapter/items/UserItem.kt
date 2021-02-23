package com.example.resume.adapter.items

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserItem(
    var name: String,
    var phone: String? = null
) : Parcelable