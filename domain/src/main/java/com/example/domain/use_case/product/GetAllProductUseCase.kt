package com.example.domain.use_case.product

import com.example.domain.repo.ProductRepository
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    suspend fun invoke(apiToken :String) = productRepository.getAllProducts(apiToken)
}