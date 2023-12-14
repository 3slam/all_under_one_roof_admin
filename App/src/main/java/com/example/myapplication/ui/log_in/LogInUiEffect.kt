package com.example.myapplication.ui.log_in

sealed interface LogInUiEffect {
    data class  LogIn (val apiToken :String): LogInUiEffect
    object GoToRegister :LogInUiEffect
}