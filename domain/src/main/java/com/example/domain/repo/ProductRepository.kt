package com.example.domain.repo

import android.net.Uri
import com.example.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun  getAllProducts(apiToken :String) : List<Product>
    suspend fun deleteProduct(apiToken :String , productId : Int) : Boolean
    suspend fun upDateProduct (apiToken :String , productId: Int, name: String, description: String, price: Int) : Boolean
    suspend fun addProduct (apiToken :String , name: String , description: String, price: Int,uri: Uri) : Boolean

}