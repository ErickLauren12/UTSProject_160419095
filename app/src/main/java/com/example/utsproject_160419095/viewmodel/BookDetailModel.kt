package com.example.utsproject_160419095.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.utsproject_160419095.model.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookDetailModel(application: Application) : AndroidViewModel(application) {
    val bookLiveData = MutableLiveData<Book>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val bookLoadErrorLiveData = MutableLiveData<Boolean>()
    val TAG = "DetailBookTag"
    private var rQueue: RequestQueue? = null

    fun detail(id: String){
        bookLoadErrorLiveData.value = false
        loadingLiveData.value = true

        rQueue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.18.19/Advance/library.php?id=$id"
        val sType = object : TypeToken<Book>(){}.type
        Log.d("Result Detail", id)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Book>(it, sType)

                loadingLiveData.value = false
                bookLiveData.value = result
                Log.d("ShowVolley", it)
            },
            {
                loadingLiveData.value = false
                bookLoadErrorLiveData.value = true
                Log.d("ErrorVolley", it.toString())
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