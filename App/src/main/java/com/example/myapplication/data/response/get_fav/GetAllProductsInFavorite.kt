package com.example.myapplication.data.response.get_fav

data class GetAllProductsInFavorite(
    val `data`: List<Data>,
    val links: Links,
    val meta: Meta
)