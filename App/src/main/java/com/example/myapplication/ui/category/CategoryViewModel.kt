package com.example.myapplication.ui.category

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
class CategoryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle ,
    private val authenticationRepositoryImp: AuthenticationRepositoryImp ,
    private val customerRepositoryImpl: CustomerRepositoryImpl
): BaseViewModel() {

    private val _uiState = MutableStateFlow(CategoryUIState())
    val  homeUiState = _uiState.asStateFlow()

    private val _categoryUiEffect = MutableSharedFlow<CategoryUiEffect>()
    val homeUiEffect = _categoryUiEffect.asSharedFlow()

    init {
       val apiKey = savedStateHandle.get<String>("api") ?: ""
        _uiState.update {
            it.copy(apiToken = apiKey)
        }

        getCategory()
    }


    private fun getCategory() = viewModelScope.launch {
      try {
        val result = customerRepositoryImpl.getAllCategories(
            _uiState.value.apiToken
         )
          _uiState.update { it.copy(categories = result ) }

         } catch (e: Exception) {
          _uiState.update { it.copy(error = e.message.toString()) }
         }
    }

    fun back() = viewModelScope.launch {
        _categoryUiEffect.emit(CategoryUiEffect.Back(
            _uiState.value.apiToken
        ))
    }

//
//    fun getAllFavorite() = viewModelScope.launch {
//        try {
//            val result = customerRepositoryImpl.getAllProductsInFavorite(
//                _categoryUiState.value.apiToken
//            )
//            Log.i("getAllFavorite VM",result.toString())
//        } catch (e: Exception) {
//            _categoryUiState.update { it.copy(error = e.message.toString()) }
//        }
//    }
//
//    fun getAllInCart() = viewModelScope.launch {
//        try {
//            val result = customerRepositoryImpl.getAllProductsInCart(
//                _categoryUiState.value.apiToken
//            )
//            Log.i("getAllInCart VM",result.toString())
//        } catch (e: Exception) {
//            _categoryUiState.update { it.copy(error = e.message.toString()) }
//        }
//    }

}