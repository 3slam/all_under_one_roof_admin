package com.example.myapplication.data.repo

import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.Product
import com.example.myapplication.data.model.ProductInCart

interface CustomerRepository {

    // Products & Categories
    suspend fun  getAllCategories(apiToken :String) : List<Category>
    suspend fun  getAllProducts(apiToken :String) : List<Product>

    // Cart
    suspend fun getAllProductsInCart(apiToken :String) : List<ProductInCart>
    suspend fun addProductToCart(apiToken :String , productId:Int) : Boolean
    suspend fun deleteProductFromCart(apiToken : String ,productName:String) : Boolean
    //Fav
    suspend fun getAllProductsInFavorite(apiToken :String) : List<Product>
    suspend fun addProductToFavorite(apiToken :String , productId:Int) : Boolean
    suspend fun deleteProductFromFavorite(apiToken : String ,productName:String) : Boolean

}