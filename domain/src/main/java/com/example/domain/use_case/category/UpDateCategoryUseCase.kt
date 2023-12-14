package com.example.domain.use_case.category

import com.example.domain.repo.CategoryRepository
import javax.inject.Inject

class UpDateCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
){
    suspend fun invoke(apiToken: String,  categoryId: Int ,categoryName: String) = categoryRepository.upDateCategory(
        apiToken,categoryId, categoryName
    )
}
