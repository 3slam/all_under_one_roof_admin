package com.example.myapplication.ui.category

sealed interface CategoryUiEffect {
 data class Back(val api :String) :CategoryUiEffect
}