package com.example.utsproject_160419095.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utsproject_160419095.R
import com.example.utsproject_160419095.model.Book
import com.example.utsproject_160419095.util.loadImage
import kotlinx.android.synthetic.main.cart_list_item.view.*

class CartAdapter(val cartList:ArrayList<Book>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CartViewHolder(inflater.inflate(R.layout.cart_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = cartList[position]
        with(holder.view){
            txtCartTitle.text = cart.title
            imgCart.loadImage(cart.photoUrl, progressBarLoadImageCart)
        }
    }

    override fun getItemCount() = cartList.size

    fun updateCartList(newCartList: ArrayList<Book>){
        cartList.clear()
        cartList.addAll(newCartList)
        notifyDataSetChanged()
    }
}