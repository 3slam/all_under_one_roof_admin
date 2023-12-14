package com.example.myapplication.data.response.get_cart

data class GetCartResponse(
    val `data`: List<Data>,
    val links: Links,
    val meta: Meta
)