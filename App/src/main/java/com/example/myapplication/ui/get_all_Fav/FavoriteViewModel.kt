package com.example.myapplication.ui.get_all_Fav

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.data.model.Product
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
class FavoriteViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
  private val customerRepositoryImpl: CustomerRepositoryImpl
    ): BaseViewModel()   {

    private val _favoriteUiState = MutableStateFlow(FavoriteUIState())
    val   productUiState = _favoriteUiState.asStateFlow()

    private val _favoriteUiEffect = MutableSharedFlow<FavoriteUiEffect>()
    val  productUiEffect = _favoriteUiEffect.asSharedFlow()

    init {
       val apiKey = savedStateHandle.get<String>("api") ?: ""
        if (apiKey!= "") {
            _favoriteUiState.update {
                it.copy(apiToken = apiKey)
            }
        }
        getAllProducts()
    }

    private fun getAllProducts() = viewModelScope.launch {
        try {
            _favoriteUiState.update { it.copy(loading = true) }
            val result = customerRepositoryImpl.getAllProductsInFavorite(_favoriteUiState.value.apiToken)
                Log.d("Product--VM",result.toString())
                _favoriteUiState.update { it.copy(products = result ,loading = false) }
        }catch (e:Exception){
            _favoriteUiState.update { it.copy(error = e.message.toString(), loading = false) }
        }
    }

    fun onBackClick()  =viewModelScope.launch {
        _favoriteUiEffect.emit(FavoriteUiEffect.Back(_favoriteUiState.value.apiToken))
    }



    fun onRefresh(){
        getAllProducts()
    }

//    override fun onDeleteClick(item: Product) {
//        removeFromFavorite(item)
//    }
//
//
//    private fun removeFromFavorite(item: Product) = viewModelScope.launch {
//        try {
//            _favoriteUiState.update { it.copy(loading = true) }
//             customerRepositoryImpl.deleteProductFromFavorite(_favoriteUiState.value.apiToken, item.name)
//            _favoriteUiState.update { it.copy(loading = false) }
//        }catch (e:Exception){
//            _favoriteUiState.update { it.copy(error = e.message.toString(), loading = false) }
//        }
//    }


}