package com.example.resume.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.resume.adapter.items.UserItem

interface Navigator {

    fun navigateBack(activity: FragmentActivity, navController: NavController)

    fun navigateToDescriptionFragment(
        activity: FragmentActivity,
        navController: NavController,
        userItem: UserItem?
    )

    fun getName(fragment: Fragment): String

    fun getItem(fragment: Fragment): UserItem?

    fun getPhoneNumber(fragment: Fragment): String?
}