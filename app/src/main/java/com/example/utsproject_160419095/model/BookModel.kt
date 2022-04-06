package com.example.utsproject_160419095.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id:String?,
    var title:String?,
    var description:String?,
    var writer:String?,
    var releaseDate:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)