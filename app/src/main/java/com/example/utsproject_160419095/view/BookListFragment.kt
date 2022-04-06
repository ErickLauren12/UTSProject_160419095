package com.example.utsproject_160419095.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsproject_160419095.R
import com.example.utsproject_160419095.viewmodel.ListBookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

class BookListFragment : Fragment() {
    private  val bookListAdapter = BookListAdapter(arrayListOf())
    private  lateinit var  viewModel: ListBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListBookViewModel::class.java)
        viewModel.refresh()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = bookListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            txtError.visibility = View.GONE
            progressLoad.visibility
            recyclerView.visibility = View.GONE
            refreshLayout.isRefreshing = false
            viewModel.refresh()
        }
    }

    private fun observeViewModel(){
        viewModel.bookLiveData.observe(viewLifecycleOwner){
            bookListAdapter.updateBookList(it)
        }

        viewModel.bookLoadErrorLiveData.observe(viewLifecycleOwner){
            txtError.visibility = if(it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){
                recyclerView.visibility = View.GONE
                progressLoad.visibility = View.VISIBLE
            }else{
                recyclerView.visibility = View.VISIBLE
                progressLoad.visibility = View.GONE
            }
        }
    }
}