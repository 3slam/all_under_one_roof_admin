package com.example.domain.repo

import com.example.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
     suspend fun getAllCategories(apiToken :String) : List<Category>
     suspend fun deleteCategory(apiToken :String , categoryId : Int) : Boolean
     suspend fun upDateCategory (apiToken: String,  categoryId: Int ,categoryName: String) : Boolean
     suspend fun addCategory (apiToken :String , name : String  ) : Boolean
}