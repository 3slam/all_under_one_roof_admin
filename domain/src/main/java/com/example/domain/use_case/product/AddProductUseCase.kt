package com.example.domain.use_case.product

import android.net.Uri
import com.example.domain.repo.ProductRepository
import javax.inject.Inject

class AddProductUseCase  @Inject constructor(
    private val productRepository: ProductRepository
){
    suspend fun invoke(apiToken :String , name:String , des :String ,price : Int , uri: Uri) =
        productRepository.addProduct(apiToken,name,des,price,uri)

}