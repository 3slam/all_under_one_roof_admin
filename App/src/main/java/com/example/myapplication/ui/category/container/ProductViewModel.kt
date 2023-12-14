package com.example.myapplication.ui.category.container

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.Log
import com.example.myapplication.base.BaseViewModel
import com.example.myapplication.data.repo.AuthenticationRepositoryImp
import com.example.myapplication.data.repo.CustomerRepositoryImpl
import com.example.myapplication.ui.home.HomeUIState
import com.example.myapplication.ui.home.HomeUiEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val customerRepositoryImpl: CustomerRepositoryImpl
): BaseViewModel()  {


    fun addToFavorite(apiToken: String, productId: Int) = viewModelScope.launch {
        customerRepositoryImpl.addProductToFavorite(apiToken, productId)
    }

    fun addToCart(apiToken: String, productId: Int) = viewModelScope.launch {
        customerRepositoryImpl.addProductToCart(apiToken, productId)
    }

}