package com.example.myapplication.data.response.get_cart

data class Data(
    val amount: Int,
    val description: String,
    val name: String,
    val price: Double,
    val price_after_discount: Double
)