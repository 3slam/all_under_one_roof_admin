package com.example.data.mapper

import com.example.data.dto.categories.Data
import com.example.domain.model.Category

object CategoryMapper : Mapper<Data, Category> {

    override fun map(input: Data): Category {
        return Category(
             createdAt = input.created_at ,
             id = input.id,
             name = input.name ,
             updatedAt =input.updated_at ,
        )
    }

    fun mapToCategoryList(input: List<Data>): List<Category> {
        return input.map {
            map(it)
        }
    }

}