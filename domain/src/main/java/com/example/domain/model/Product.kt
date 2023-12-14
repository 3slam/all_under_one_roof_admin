package com.example.domain.model

data class Product(
    val category :String ,
    val createdAt: String,
    val description: String,
    val id: Int,
    val name: String,
    val url : String?,
    val price: Double,
    val priceAfterDiscount: Double,
    val updatedAt: String
)