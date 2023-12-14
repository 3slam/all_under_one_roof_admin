package com.example.data.mapper

import com.example.data.dto.products.Data
import com.example.domain.model.Product


object ProductMapper : Mapper<Data, Product> {

    override fun map(input: Data): Product {
        return Product(
            createdAt = input.created_at,
            description = input.description,
            id = input.id,
            name = input.name,
            price = input.price,
            url = input.images.get(0)?.url,
            priceAfterDiscount = input.price_after_discount,
            updatedAt = input.updated_at
        )
    }

    fun mapToProductList(input: List<Data>): List<Product> {
        return input.map {
            map(it)
        }
    }

}