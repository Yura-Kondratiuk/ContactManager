package com.example.resume.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.example.resume.R
import com.example.resume.adapter.items.UserItem
import com.example.resume.utils.navigateSafe

class NavigatorImpl() : Navigator {

    companion object {
        private const val NAVIGATION_NAME_ID = "NAVIGATION_NAME_ID"
        private const val NAVIGATION_PHONE_ID = "NAVIGATION_PHONE_ID"
    }

    override fun navigateBack(activity: FragmentActivity, navController: NavController) {
        navController.popBackStack()
    }

    override fun navigateToDescriptionFragment(
        activity: FragmentActivity,
        navController: NavController,
        userItem: UserItem?
    ) {
        navController.navigateSafe(R.id.action_mainFragment_to_descriptionFragment,
            Bundle().apply {
                putString(NAVIGATION_NAME_ID, userItem?.name)
                putString(NAVIGATION_PHONE_ID, userItem?.phone)
            })
    }

    override fun getName(fragment: Fragment): String =
        fragment.arguments?.getString(NAVIGATION_NAME_ID, "") ?: ""

    override fun getItem(fragment: Fragment): UserItem? =
        fragment.arguments?.getParcelable("")

    override fun getPhoneNumber(fragment: Fragment): String? =
        fragment.arguments?.getString(NAVIGATION_PHONE_ID, "")
}