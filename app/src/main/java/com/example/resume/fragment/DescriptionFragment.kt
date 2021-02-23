package com.example.resume.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.resume.R
import com.example.resume.adapter.ItemClickListener
import com.example.resume.adapter.SelectImagePathAdapter
import com.example.resume.adapter.items.SelectImagePathItem
import com.example.resume.navigation.NavigatorImpl
import com.example.resume.utils.SelectImagePathEnum
import com.example.resume.utils.hideKeyboard
import com.example.resume.viewmodel.DescriptionViewModel
import kotlinx.android.synthetic.main.fragment_description.*
import java.util.*


class DescriptionFragment : Fragment(), View.OnClickListener {
    companion object {
        private const val ACTIVITY_SELECT_IMAGE = 1111
        private const val REQUEST_CODE = 2222
    }

    private lateinit var viewModel: DescriptionViewModel
    private val navigator = NavigatorImpl()

    private val listener = object : ItemClickListener {
        override fun onSelectImagePathItemClicked(
            holder: RecyclerView.ViewHolder?,
            selectImagePathItem: SelectImagePathItem?,
            pos: Int
        ) {
            when(selectImagePathItem?.type) {
                SelectImagePathEnum.GALLERY -> openGallery()
                SelectImagePathEnum.CAMERA -> openCamera()
                SelectImagePathEnum.FILES -> {
                    Log.e("tag", "files were clicked")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        viewModel = ViewModelProvider(this).get(DescriptionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initListeners()
        initRecyclerView()
        observeData()
        tvPhone.text = navigator.getPhoneNumber(this)
    }

    private fun initToolbar() {
        tvTitle.text = navigator.getName(this).toUpperCase(Locale.ROOT)
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setNavigationIcon(R.drawable.ic_left_arrow)
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            this?.layoutManager = LinearLayoutManager(activity)
            this?.adapter = SelectImagePathAdapter(
                viewModel.getSelectImagePathItems(requireActivity()),
                listener
            )
        }
    }

    private fun initListeners() {
        val itemChangeAll = view?.findViewById<View>(R.id.itemEdit)
        toolbar.setNavigationOnClickListener {
            navigator.navigateBack(requireActivity(), findNavController())
        }
        ivImage.setOnClickListener(this)
        itemChangeAll?.setOnClickListener(this)
        tvCross.setOnClickListener(this)
        tvPhone.setOnClickListener(this)
    }

    private fun observeData() {
        viewModel.isEditableUpdate.observe(viewLifecycleOwner, Observer {
            view?.hideKeyboard()
            changePhoneMode(it)
            changeIconMode(it)
            changeSelectImageMode(it)
        })
    }

    private fun updateImage(imageView: ImageView, drawable: Drawable) {
        Glide.with(requireContext())
            .load(drawable)
            .apply(RequestOptions().circleCrop())
            .into(imageView)
        updateFullImage(drawable)
    }

    private fun updateFullImage(drawable: Drawable) {
        Glide.with(requireContext())
            .load(drawable)
            .into(ivFullImage)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ivImage -> {
                when (viewModel.isEditableUpdate.value ?: false) {
                    true -> selectImagePath()
                    false -> openImage()
                }
            }
            R.id.tvCross -> imageContainer?.visibility = View.GONE
            R.id.itemEdit -> changeEditMode()
            R.id.tvPhone -> callNumber(tvPhone.text.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == ACTIVITY_SELECT_IMAGE) {
            val drawable = viewModel.getDrawableFromUri(requireActivity(), data?.data ?: return)
            updateImage(ivImage, drawable)
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            val drawable = viewModel.getDrawableFromBitmap(requireActivity(), data)
            updateImage(ivImage, drawable)
        }
    }

    private fun selectImagePath() {
        recycler_view.visibility = View.VISIBLE
    }

    private fun openImage() {
        viewModel.currentDrawable?.let {
            imageContainer?.visibility = View.VISIBLE
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, ACTIVITY_SELECT_IMAGE)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    private fun callNumber(number: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    private fun changeEditMode() {
        viewModel.isEditableUpdate.value = viewModel.isEditableUpdate.value?.not()
    }

    private fun changePhoneMode(isEditable: Boolean) {
        if (isEditable) {
            tvPhone.visibility = View.GONE
            etPhone.visibility = View.VISIBLE
        } else {
            tvPhone.visibility = View.VISIBLE
            etPhone.visibility = View.GONE
        }
    }

    private fun changeIconMode(isEditable: Boolean) {
        if (isEditable) {
            toolbar.menu.getItem(0).icon = null
        } else {
            toolbar.menu.getItem(0).icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_edit)
        }
    }

    private fun changeSelectImageMode(isEditable: Boolean) {
        linear_layout.visibility = if (isEditable) View.VISIBLE else View.GONE
    }
}