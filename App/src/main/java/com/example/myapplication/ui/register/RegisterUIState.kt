package com.example.myapplication.ui.register

data class RegisterUIState(

    val email :String = "",
    val password :String = "",
    val phone :String = "",
    val name :String = "",
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
