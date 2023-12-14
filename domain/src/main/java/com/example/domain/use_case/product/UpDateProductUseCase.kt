package com.example.domain.use_case.product

import com.example.domain.repo.ProductRepository
import javax.inject.Inject

class UpDateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    suspend fun invoke(apiToken :String , productId: Int, name: String, description: String, price: Int) =
        productRepository.upDateProduct(apiToken,productId, name, description, price)

}