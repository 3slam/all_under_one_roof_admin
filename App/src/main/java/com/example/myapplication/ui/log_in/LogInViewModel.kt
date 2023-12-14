package com.example.myapplication.ui.log_in

import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.data.repo.AuthenticationRepositoryImp
import com.example.myapplication.uitiles.InputValidationState
import com.example.myapplication.uitiles.InputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authenticationRepositoryImp: AuthenticationRepositoryImp
): BaseViewModel() {

    private val _loginUIState = MutableStateFlow(LoginUIState())
    val loginUIState = _loginUIState.asStateFlow()

    private val _loginUiEffect  = MutableSharedFlow<LogInUiEffect>()
    val  loginUiEffect = _loginUiEffect.asSharedFlow()


    private fun resetEmailAndPasswordAndErrorMessage() = _loginUIState.update {it.copy(password = "" , isError = false)}

    fun onClickLogIn() = logInWithEmailANdPassword()

     fun GoToRegister() = viewModelScope.launch {
         _loginUiEffect.emit(LogInUiEffect.GoToRegister)
     }
    private fun logInWithEmailANdPassword() = viewModelScope.launch {
         _loginUIState.update { it.copy(isLoading = true) }
        try {
            val result = authenticationRepositoryImp.signIn(
                _loginUIState.value.email ,
                _loginUIState.value.password
            )
            _loginUIState.update { it.copy(apiToken = result) }
            _loginUiEffect.emit(LogInUiEffect.LogIn(_loginUIState.value.apiToken))
        }catch (e:Exception){
            _loginUIState.update { it.copy(error = e.message.toString()) }
            resetEmailAndPasswordAndErrorMessage()
        }
    }



    fun onEmailInputChange(text: CharSequence) {
        val emailValidationState = InputValidator.checkEmailValidation(text.toString())
        _loginUIState.update {
            it.copy(
                email = text.toString() ,
                emailHelperText = if (emailValidationState is InputValidationState.InValid)
                    emailValidationState.message else "" ,
                isEmailValidation = emailValidationState is InputValidationState.InValid ,
                isEmailAndPasswordAreValidation = InputValidator.emailAndPasswordIsValid(
                    _loginUIState.value.email ,   _loginUIState.value.password)
            )
        }
    }

    fun onPasswordInputChange(text: CharSequence) {
        val passwordValidationState = InputValidator.checkPasswordValidation(text.toString())
        _loginUIState.update {
            it.copy(
                password = text.toString() ,
                passwordHelperText = if (passwordValidationState is InputValidationState.InValid)
                    passwordValidationState.message else "" ,
                isPasswordValidation = passwordValidationState is InputValidationState.InValid ,
                isEmailAndPasswordAreValidation = InputValidator.emailAndPasswordIsValid(
                    _loginUIState.value.email ,   _loginUIState.value.password)
            )
        }
    }
}