package com.example.myapplication.data

import com.example.myapplication.data.model.Category
import com.example.myapplication.data.model.Product
import com.example.myapplication.data.model.ProductInCart
import com.example.myapplication.data.response.get_category.Data

object Mapper {


    fun mapToCategoryList(input: List<Data>): List<Category> {
        return input.map { mapToCategory(it) }
    }

    private fun mapToCategory(input: Data): Category {
        return Category(
            createdAt = input.created_at,
            id = input.id,
            name = input.name,
            product = mapToProductListFromProductInCategory(input, input.products),
            updatedAt = input.updated_at
        )
    }

    private fun mapToProductFromProductInCategory(
        data: Data,
        input: com.example.myapplication.data.response.get_category.Product
    ): Product {
        return Product(
            category = data.name,
            createdAt = input.created_at,
            description = input.description,
            id = input.id,
            name = input.name,
            url = if (input.images.isNotEmpty()) input.images[0].url else "https://cdn-icons-png.flaticon.com/256/13372/13372731.png",
            price = input.price.toDouble(),
            priceAfterDiscount = input.price_after_discount.toDouble(),
            updatedAt = input.updated_at
        )
    }

    private fun mapToProductListFromProductInCategory(
        data: Data,
        input: List<com.example.myapplication.data.response.get_category.Product>
    ): List<Product> {
        return input.map { mapToProductFromProductInCategory(data, it) }
    }

    private fun mapToProduct(input: com.example.myapplication.data.response.get_product.Data): Product {
        return Product(
            category = if (input.categories.isNotEmpty()) input.categories[0].name else "No Category",
            createdAt = input.created_at,
            description = input.description,
            id = input.id,
            name = input.name,
            url = if (input.images.isNotEmpty()) input.images[0].url else "https://cdn-icons-png.flaticon.com/256/13372/13372731.png",
            price = input.price.toDouble(),
            priceAfterDiscount = input.price_after_discount.toDouble(),
            updatedAt = input.updated_at
        )
    }

    fun mapToProductList(
        input: List<com.example.myapplication.data.response.get_product.Data>
    ): List<Product> {
        return input.map { mapToProduct(it) }
    }


    private fun mapToProductInCart(input: com.example.myapplication.data.response.get_cart.Data): ProductInCart {
        return ProductInCart(
            name = input.name,
            description = input.description,
            price = input.price,
            price_after_discount = input.price_after_discount

        )
    }

    fun mapToListProductInCart(input: List<com.example.myapplication.data.response.get_cart.Data>): List<ProductInCart> {
        return input.map {
            mapToProductInCart(it)
        }
    }


    private fun mapToProductInFavourite(input: com.example.myapplication.data.response.get_fav.Data): Product {
        return Product(
            category = "",
            createdAt = input.created_at,
            description = input.description,
            id = input.id,
            name = input.name,
            url = "https://cdn-icons-png.flaticon.com/256/13372/13372731.png",
            price = input.price.toDoubleOrNull() ?: 0.0,
            priceAfterDiscount = input.price_after_discount.toDoubleOrNull() ?: 0.0,
            updatedAt = input.updated_at
        )
    }

    fun mapToListProductInFavourite(input: List<com.example.myapplication.data.response.get_fav.Data>): List<Product> {
        return input.map {
            mapToProductInFavourite(it)
        }
    }

}