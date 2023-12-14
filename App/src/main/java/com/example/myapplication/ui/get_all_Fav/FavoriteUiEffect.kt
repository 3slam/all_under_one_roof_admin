package com.example.myapplication.ui.get_all_Fav

sealed interface FavoriteUiEffect {
    data class Back(val api :String):FavoriteUiEffect
}