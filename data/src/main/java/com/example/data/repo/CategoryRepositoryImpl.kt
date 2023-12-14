package com.example.data.repo

import android.util.Log
import com.example.data.mapper.CategoryMapper
import com.example.myapplication.data.response.get_product_with_category.service.CategoryService
import com.example.domain.model.Category
import com.example.domain.repo.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val  categoryService: com.example.myapplication.data.response.get_product_with_category.service.CategoryService
) : CategoryRepository {
    override suspend fun getAllCategories(apiToken: String): List<Category>   {
        return try {
         val result = categoryService.getAllCategories(apiToken)
          Log.d("Category--get",result.body().toString())
          CategoryMapper.mapToCategoryList(result.body()!!.data)
        }catch (e:Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun upDateCategory(apiToken: String,  categoryId: Int ,categoryName: String): Boolean {
        return  try {
            val result =categoryService.updateCategory(apiToken,categoryId,categoryName)
            Log.d("Category--upDate",result.body().toString())
            true
        }catch (e:Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun addCategory(apiToken: String, name: String) : Boolean{
        return  try {
            val result =categoryService.addCategory(apiToken,name )
            Log.d("Category--add",result.code().toString())
            true
        }catch (e:Exception) {
            throw Exception("The selected parent id is invalid")
        }
    }

    override suspend fun deleteCategory(apiToken: String, categoryId: Int): Boolean {
       return try {
           val result = categoryService.deleteCategory(apiToken,categoryId)
           Log.d("Category--delete",result.toString())
           true
       } catch (e:Exception) {
            throw Exception(e.message)
        }
    }



}

