package com.example.domain.use_case.product

import com.example.domain.repo.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase  @Inject constructor(
    private val productRepository: ProductRepository
){
    suspend fun invoke(apiToken :String , productId : Int) = productRepository.deleteProduct(apiToken,productId)

}