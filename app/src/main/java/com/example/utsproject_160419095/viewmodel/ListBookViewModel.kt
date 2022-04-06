package com.example.utsproject_160419095.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.utsproject_160419095.model.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListBookViewModel(application: Application) : AndroidViewModel(application) {
    val bookLiveData = MutableLiveData<ArrayList<Book>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val bookLoadErrorLiveData = MutableLiveData<Boolean>()
    val TAG = "listBookTag"
    private var rQueue: RequestQueue? = null

    fun refresh(){
        rQueue = Volley.newRequestQueue(getApplication())

        loadingLiveData.value = true
        bookLoadErrorLiveData.value = false

        val url = "http://192.168.18.19/Advance/book.json"
        val sType = object : TypeToken<ArrayList<Book>>() {}.type

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)

                loadingLiveData.value = false
                bookLiveData.value = result
            },
            {
                bookLoadErrorLiveData.value = true
                loadingLiveData.value = false
            }
        ).apply {
            tag = "TAG"
        }

        rQueue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        rQueue?.cancelAll(TAG)
    }
}