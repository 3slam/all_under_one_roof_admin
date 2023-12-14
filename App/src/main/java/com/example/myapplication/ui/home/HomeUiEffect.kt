package com.example.myapplication.ui.home

sealed interface HomeUiEffect {
  object SignOut : HomeUiEffect
  data class GoToCategory(val apiToken : String) : HomeUiEffect
  data class GoToFav(val apiToken : String) : HomeUiEffect
  data class GoToCart(val apiToken : String) : HomeUiEffect
}