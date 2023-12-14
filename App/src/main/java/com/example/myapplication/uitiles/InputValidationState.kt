package com.example.myapplication.uitiles

sealed class InputValidationState {
    object Valid : InputValidationState()
    data class InValid(val message: String) : InputValidationState()
}