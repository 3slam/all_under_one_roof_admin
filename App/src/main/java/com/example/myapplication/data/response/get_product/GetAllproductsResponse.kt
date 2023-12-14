package com.example.myapplication.data.response.get_product

data class GetAllproductsResponse(
    val `data`: List<Data>,
    val links: Links,
    val meta: Meta
)