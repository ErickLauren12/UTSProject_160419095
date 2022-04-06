package com.example.utsproject_160419095.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.utsproject_160419095.R
import com.example.utsproject_160419095.util.loadImage
import com.example.utsproject_160419095.viewmodel.BookDetailModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment : Fragment() {
    private lateinit var viewModel: BookDetailModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BookDetailModel::class.java)
        val id = BookDetailFragmentArgs.fromBundle(requireArguments()).bookId
        viewModel.detail(id)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            imgBookDetail.loadImage(it.photoUrl,progressBookDetailLoad)
            txtTitleDetail.text = it.title
            txtWriterDetail.text = it.writer
            txtReleaseDate.text = it.releaseDate
            txtDescriptionDetail.text = it.description
        }
    }
}