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

class CartModel(application: Application) : AndroidViewModel(application) {
    val cartLiveData = MutableLiveData<ArrayList<Book>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val cartLoadErrorLiveData = MutableLiveData<Boolean>()
    val TAG = "cartTag"
    private var rQueue: RequestQueue? = null

    fun refresh(){
        cartLoadErrorLiveData.value = false
        loadingLiveData.value = true

        rQueue = Volley.newRequestQueue(getApplication())
        val url = "http://192.168.18.19/Advance/cart.json"
        val sType = object : TypeToken<ArrayList<Book>>() {}.type

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<ArrayList<Book>>(it, sType)

                loadingLiveData.value = false
                cartLiveData.value = result
            },
            {
                loadingLiveData.value = false
                cartLoadErrorLiveData.value = true
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