package com.example.utsproject_160419095.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.utsproject_160419095.R
import com.example.utsproject_160419095.model.Book
import com.example.utsproject_160419095.util.loadImage
import kotlinx.android.synthetic.main.book_list_item.view.*

class BookListAdapter(val bookList:ArrayList<Book>) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {
    class BookViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return  BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = bookList[position]
        with(holder.view){
            txtTitle.text = book.title
            txtDescription.text = book.description
            imageBook.loadImage(book.photoUrl, progressLoadingPhoto)

            btnDetail.setOnClickListener {
                val action = BookListFragmentDirections.actionToBookDetail(book.id.toString())
                Navigation.findNavController(it).navigate(action)
            }

        }
    }

    override fun getItemCount() = bookList.size

    fun updateBookList(newBookList: ArrayList<Book>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }
}