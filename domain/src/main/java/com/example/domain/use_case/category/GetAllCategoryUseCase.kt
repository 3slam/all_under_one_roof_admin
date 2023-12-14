package com.example.domain.use_case.category

import com.example.domain.repo.AuthenticationRepository
import com.example.domain.repo.CategoryRepository
import javax.inject.Inject

class GetAllCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
){
    suspend fun invoke(apiToken :String) = categoryRepository.getAllCategories(apiToken)
}
