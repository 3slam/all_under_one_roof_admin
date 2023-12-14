package com.example.myapplication.data.repo

import android.util.Log
import com.example.myapplication.data.Mapper
import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.Product
import com.example.myapplication.data.model.ProductInCart
import com.example.myapplication.data.service.CustomerService
import javax.inject.Inject


class CustomerRepositoryImpl @Inject constructor(
    private val customerService: CustomerService
): CustomerRepository {

    override suspend fun getAllCategories(apiToken: String): List<Category> {
        return try {
            val result = customerService.getAllCategories(apiToken,"products")
            Log.d("Customer--getCat" , result.body()!!.data.toString())
            val x = Mapper.mapToCategoryList(result.body()?.data ?: emptyList())
            Log.d("Customer--getCat2" , x.toString())
            x
        }catch (e:Exception){
            throw Exception("Error")
        }
    }

    override suspend fun getAllProducts(apiToken: String): List<Product> {
        return try {
            val result = customerService.getAllProducts(apiToken,"categories")
            Log.d("Customer--getPro" , result.body()!!.data.toString())
             val x = Mapper.mapToProductList(result.body()?.data ?: emptyList())
            Log.d("Customer--getPro2" ,x.toString())
              x
        }catch (e:Exception){
            throw Exception("Error")
        }
    }

    override suspend fun getAllProductsInCart(apiToken: String): List<ProductInCart> {
        return try {
            val result = customerService.getAllProductsInCart(apiToken )
            Log.d("Customer--getcCart" , result.body()!!.data.toString())
            val x = Mapper.mapToListProductInCart(result.body()?.data ?: emptyList())
            Log.d("Customer--getcCart" ,x.toString())
            x
        }catch (e:Exception){
            throw Exception("Error")
        }
    }

    override suspend fun addProductToCart(apiToken: String, productId: Int): Boolean {
        return try {
            val result = customerService.addProductToCart(apiToken,productId)
            Log.d("Customer--addProductToCart" , result.code().toString())
            true
        }catch (e:Exception){
            throw Exception("Error")
        }
    }

    override suspend fun deleteProductFromCart(apiToken: String, productName: String): Boolean {
        return try {
            val result = customerService.deleteProductFromCart(apiToken,productName  )
            Log.d("Customer--deleteProductFromCart" , result.code().toString())
            true
        }catch (e:Exception){
            throw Exception("Error")
        }
    }

    override suspend fun getAllProductsInFavorite(apiToken: String): List<Product> {
        return try {
            val result = customerService.getAllProductsInFavourite(apiToken )
            Log.d("Customer--getFAV" , result.body()!!.data.toString())
            val x = Mapper.mapToListProductInFavourite(result.body()?.data ?: emptyList())
            Log.d("Customer--getFAV" ,x.toString())
            x
        }catch (e:Exception){
            throw Exception("Error")
        }
    }

    override suspend fun addProductToFavorite(apiToken: String, productId: Int): Boolean {
        return try {
            val result = customerService.addProductToFavourite(apiToken,productId)
            Log.d("Customer--addProductToFavorite" , result.code().toString())
            true
        }catch (e:Exception){
            throw Exception("Error")
        }
    }

    override suspend fun deleteProductFromFavorite(apiToken: String, productName: String): Boolean {
        return try {
            val result = customerService.deleteProductFromFavourite(apiToken,productName)
            Log.d("Customer--deleteProductFromFavorite" , result.code().toString())
            true
        }catch (e:Exception){
            throw Exception("Error")
        }
    }

}