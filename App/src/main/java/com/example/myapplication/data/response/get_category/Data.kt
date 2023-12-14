package com.example.myapplication.data.response.get_category

data class Data(
    val created_at: String,
    val id: Int,
    val name: String,
    val products: List<Product>,
    val updated_at: String
)