package com.example.myapplication.ui.category

import com.example.myapplication.data.model.Category

data class CategoryUIState(
    val apiToken :String = "",
    val error:String = "",
    val categories : List<Category> = emptyList()
)
