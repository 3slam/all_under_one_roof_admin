package com.example.myapplication.ui.get_all_in_cart

sealed interface CartUiEffect {
    data class Back(val api :String):CartUiEffect
}