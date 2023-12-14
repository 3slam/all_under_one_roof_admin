package com.example.myapplication.ui.get_all_Fav


import com.example.myapplication.data.model.Product

data class FavoriteUIState(
    val apiToken :String = "",
    val error:String = "",
    val loading :Boolean = false ,
    val products : List<Product> = emptyList()
)
