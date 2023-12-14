package com.example.myapplication.ui.register

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
class RegisterViewModel @Inject constructor(
    private val authenticationRepositoryImp: AuthenticationRepositoryImp
): BaseViewModel() {

    private val _uiState = MutableStateFlow(RegisterUIState())
    val uiState = _uiState.asStateFlow()

    private val _loginUiEffect  = MutableSharedFlow<RegisterUiEffect>()
    val  loginUiEffect = _loginUiEffect.asSharedFlow()


    private fun resetEmailAndPasswordAndErrorMessage(): Unit = _uiState.update {it.copy(password = "" , isError = false)}

    fun onClickLogIn() = logInWithEmailANdPassword()

    fun goToLogIn() = viewModelScope.launch {
        _loginUiEffect.emit(RegisterUiEffect.GoToLogIn)
    }
    private fun logInWithEmailANdPassword() = viewModelScope.launch {
         _uiState.update { it.copy(isLoading = true) }
        try {

            if (_uiState.value.email.isBlank() || _uiState.value.name.isBlank() ||
                _uiState.value.phone.isBlank() || _uiState.value.password.isBlank()
            ){
                _loginUiEffect.emit(RegisterUiEffect.CompleteALlInfo)
            }else {

                val result = authenticationRepositoryImp.register(
                    _uiState.value.name ,
                    _uiState.value.phone,
                    _uiState.value.email ,
                    _uiState.value.password)

                _uiState.update { it.copy(apiToken = result) }
                _loginUiEffect.emit(RegisterUiEffect.Register(_uiState.value.apiToken))
            }
        }catch (e:Exception){
            _uiState.update { it.copy(error = e.message.toString()) }
            resetEmailAndPasswordAndErrorMessage()
        }
    }



    fun onEmailInputChange(text: CharSequence) {
        val emailValidationState = InputValidator.checkEmailValidation(text.toString())
        _uiState.update {
            it.copy(
                email = text.toString() ,
                emailHelperText = if (emailValidationState is InputValidationState.InValid)
                    emailValidationState.message else "" ,
                isEmailValidation = emailValidationState is InputValidationState.InValid ,
                isEmailAndPasswordAreValidation = InputValidator.emailAndPasswordIsValid(
                    _uiState.value.email ,   _uiState.value.password)
            )
        }
    }

    fun onNameInputChange(text: CharSequence) {
        _uiState.update {
            it.copy(
                name = text.toString()
            )
        }
    }

    fun onPhoneInputChange(text: CharSequence) {
        _uiState.update {
            it.copy(
                phone = text.toString()
            )
        }
    }


    fun onPasswordInputChange(text: CharSequence) {
        val passwordValidationState = InputValidator.checkPasswordValidation(text.toString())
        _uiState.update {
            it.copy(
                password = text.toString() ,
                passwordHelperText = if (passwordValidationState is InputValidationState.InValid)
                    passwordValidationState.message else "" ,
                isPasswordValidation = passwordValidationState is InputValidationState.InValid ,
                isEmailAndPasswordAreValidation = InputValidator.emailAndPasswordIsValid(
                    _uiState.value.email ,   _uiState.value.password)
            )
        }
    }
}