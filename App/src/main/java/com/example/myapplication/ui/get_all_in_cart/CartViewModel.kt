package com.example.myapplication.ui.get_all_in_cart

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.data.model.Product
import com.example.myapplication.data.model.ProductInCart
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
class CartViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
  private val customerRepositoryImpl: CustomerRepositoryImpl
    ): BaseViewModel() {

    private val _cartUiState = MutableStateFlow(CartUIState())
    val   productUiState = _cartUiState.asStateFlow()

    private val _cartUiEffect = MutableSharedFlow<CartUiEffect>()
    val  productUiEffect = _cartUiEffect.asSharedFlow()

    init {
       val apiKey = savedStateHandle.get<String>("api") ?: ""
        if (apiKey!= "") {
            _cartUiState.update {
                it.copy(apiToken = apiKey)
            }
        }
        getAllProducts()
    }

    private fun getAllProducts() = viewModelScope.launch {
        try {
            _cartUiState.update { it.copy(loading = true) }
            val result = customerRepositoryImpl.getAllProductsInCart(_cartUiState.value.apiToken)
                Log.d("Product--VM",result.toString())
                _cartUiState.update { it.copy(products = result ,loading = false) }
        }catch (e:Exception){
            _cartUiState.update { it.copy(error = e.message.toString(), loading = false) }
        }
    }

    fun onBackClick()  =viewModelScope.launch {
        _cartUiEffect.emit(CartUiEffect.Back(_cartUiState.value.apiToken))
    }

    fun onRefresh(){
        getAllProducts()
    }

//    override fun onDeleteClick(item: ProductInCart) {
//        removeFromCart(item)
//    }
//
//
//    private fun removeFromCart(item: ProductInCart) = viewModelScope.launch {
//        try {
//            _cartUiState.update { it.copy(loading = true) }
//            customerRepositoryImpl.deleteProductFromCart(_cartUiState.value.apiToken, item.name)
//            _cartUiState.update { it.copy(loading = false) }
//        }catch (e:Exception){
//            _cartUiState.update { it.copy(error = e.message.toString(), loading = false) }
//        }
//    }


}