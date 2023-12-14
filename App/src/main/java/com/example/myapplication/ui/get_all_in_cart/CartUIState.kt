package com.example.myapplication.ui.get_all_in_cart

import com.example.myapplication.data.model.ProductInCart

data class CartUIState(
    val apiToken :String = "",
    val error:String = "",
    val loading :Boolean = false ,
    val products : List<ProductInCart> = emptyList()
)
