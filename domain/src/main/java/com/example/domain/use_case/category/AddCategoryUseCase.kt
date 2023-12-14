package com.example.domain.use_case.category

import com.example.domain.repo.CategoryRepository
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(
private val categoryRepository: CategoryRepository
){
    suspend fun invoke(apiToken :String ,name :String) =
        categoryRepository.addCategory(apiToken,name )
}

