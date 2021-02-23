package com.example.resume.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resume.R
import com.example.resume.adapter.items.UserItem
import com.example.resume.adapter.ItemClickListener
import com.example.resume.adapter.MainAdapter
import com.example.resume.navigation.NavigatorImpl
import com.example.resume.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val navigator = NavigatorImpl()

    private val list = listOf(
        UserItem("Yura Kondratiuk", "0939433283"),
        UserItem("Vlad", "0982511775"),
        UserItem("Alex", "0939433283"),
        UserItem("Vegos", "0982511775"),
        UserItem("Diana", "0939433283"),
        UserItem("Vlad", "0982511775"),
        UserItem("Max", "0939433283"),
        UserItem("Vlad", "0982511775"),
        UserItem("Yura"),
        UserItem("Vlad"),
        UserItem("Yura"),
        UserItem("Vlad"),
        UserItem("Vlad")
    )

    private lateinit var viewModel: MainViewModel

    private val listener = object : ItemClickListener {
        override fun onItemClicked(holder: RecyclerView.ViewHolder?, userItem: UserItem?, pos: Int) {
            navigator.navigateToDescriptionFragment(requireActivity(), findNavController(), userItem)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            this?.layoutManager = LinearLayoutManager(activity)
            this?.adapter = MainAdapter(list, listener)
        }
    }
}