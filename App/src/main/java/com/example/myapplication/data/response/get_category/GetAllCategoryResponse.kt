package com.example.myapplication.data.response.get_category

data class GetAllCategoryResponse(
    val `data`: List<Data>,
    val links: Links,
    val meta: Meta
)