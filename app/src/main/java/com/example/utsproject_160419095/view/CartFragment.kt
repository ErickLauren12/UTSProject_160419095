package com.example.utsproject_160419095.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utsproject_160419095.R
import com.example.utsproject_160419095.viewmodel.CartModel
import kotlinx.android.synthetic.main.fragment_cart_list.*

class CartFragment : Fragment() {
    private  lateinit var  viewModel: CartModel
    private  val cartListAdapter = CartAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(CartModel::class.java)
        viewModel.refresh()

        recyclerViewCart.layoutManager = LinearLayoutManager(context)
        recyclerViewCart.adapter = cartListAdapter

        observeViewModel()

        refreshLayoutCart.setOnRefreshListener {
            recyclerViewCart.visibility = View.GONE
            txtErrorCart.visibility = View.GONE
            progressLoadCart.visibility
            viewModel.refresh()
            refreshLayoutCart.isRefreshing = false
        }
    }

    private fun observeViewModel(){
        viewModel.cartLiveData.observe(viewLifecycleOwner){
            cartListAdapter.updateCartList(it)
        }

        viewModel.cartLoadErrorLiveData.observe(viewLifecycleOwner){
            txtErrorCart.visibility = if(it) View.VISIBLE else View.GONE
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            if(it){
                recyclerViewCart.visibility = View.GONE
                progressLoadCart.visibility = View.VISIBLE
            }else{
                recyclerViewCart.visibility = View.VISIBLE
                progressLoadCart.visibility = View.GONE
            }
        }
    }
}