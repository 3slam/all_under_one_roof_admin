package com.example.myapplication.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.data.repo.AuthenticationRepositoryImp
import com.example.myapplication.data.repo.CustomerRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle ,
    private val authenticationRepositoryImp: AuthenticationRepositoryImp ,
    private val customerRepositoryImpl: CustomerRepositoryImpl
): BaseViewModel() {

    private val _homeUiState = MutableStateFlow(HomeUIState())
    val  homeUiState = _homeUiState.asStateFlow()

    private val _homeUiEffect = MutableSharedFlow<HomeUiEffect>()
    val homeUiEffect = _homeUiEffect.asSharedFlow()

    init {
       val apiKey = savedStateHandle.get<String>("api") ?: ""
        _homeUiState.update {
            it.copy(apiToken = apiKey)
        }
    }

   fun onSignOutClick() = viewModelScope.launch {
       try {
           val result = authenticationRepositoryImp.signOut(_homeUiState.value.apiToken )
           _homeUiEffect.emit(HomeUiEffect.SignOut)
       }catch (e:Exception){
           _homeUiState.update { it.copy(error = e.message.toString()) }
       }
   }


//
    fun getCategory() = viewModelScope.launch {
    _homeUiEffect.emit(HomeUiEffect.GoToCategory(
        _homeUiState.value.apiToken
    ))
//      try {
//        val result = customerRepositoryImpl.getAllCategories(
//            _homeUiState.value.apiToken
//         )
//         Log.i("getCategory VM",result.toString())
//         } catch (e: Exception) {
//         _homeUiState.update { it.copy(error = e.message.toString()) }
//         }
     }

    fun getProducts() = viewModelScope.launch {
        try {
            val result = customerRepositoryImpl.getAllProducts(
             _homeUiState.value.apiToken
            )
            Log.i("getProducts VM",result.toString())
        } catch (e: Exception) {
            _homeUiState.update { it.copy(error = e.message.toString()) }
        }
    }


    fun goToFavorite() = viewModelScope.launch {
        _homeUiEffect.emit(HomeUiEffect.GoToFav(
            _homeUiState.value.apiToken
        ))
    }

    fun goToCart() = viewModelScope.launch {
        _homeUiEffect.emit(HomeUiEffect.GoToCart(
            _homeUiState.value.apiToken
        ))
    }

}