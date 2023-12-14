package com.example.myapplication.data.response.get_fav

data class Data(
    val created_at: String,
    val description: String,
    val id: Int,
    val images: List<Image>,
    val name: String,
    val price: String,
    val price_after_discount: String,
    val updated_at: String
)

data class Image(
    val id: Int,
    val url :String
)