package com.example.myapplication.ui.register

sealed interface RegisterUiEffect {
    data class  Register (val apiToken :String): RegisterUiEffect
    object  CompleteALlInfo : RegisterUiEffect
    object  GoToLogIn : RegisterUiEffect
}