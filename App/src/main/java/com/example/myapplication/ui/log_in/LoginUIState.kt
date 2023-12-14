package com.example.myapplication.ui.log_in

data class LoginUIState(
    val email :String = "12345@gmail.com",
    val password :String = "password",
    val emailHelperText :String = "",
    val passwordHelperText :String = "",
    val isLoading:Boolean = false,
    val isEmailValidation: Boolean = false ,
    val isPasswordValidation: Boolean = false,
    val isEmailAndPasswordAreValidation : Boolean =false ,
    val isError : Boolean =false ,
    val error:String = "",
    val isLogInSuccess:Boolean = false ,
    val apiToken : String = ""
)
