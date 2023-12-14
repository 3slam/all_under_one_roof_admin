package com.example.domain.use_case.category

import com.example.domain.repo.CategoryRepository
import javax.inject.Inject

class DeleteCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
){
    suspend fun invoke(apiToken :String , categoryId : Int) = categoryRepository.deleteCategory(apiToken,categoryId)
}

