package com.example.myapplication.data.service


import com.example.myapplication.data.response.get_cart.GetCartResponse
import com.example.myapplication.data.response.get_category.GetAllCategoryResponse
import com.example.myapplication.data.response.get_fav.GetAllProductsInFavorite
import com.example.myapplication.data.response.get_product.GetAllproductsResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CustomerService {

    @GET("categories")
    suspend fun getAllCategories(
        @Header("Authorization") token: String,
        @Query("with") with :String
    ) : Response<GetAllCategoryResponse>


    @GET("products")
    suspend fun getAllProducts(
        @Header("Authorization") token: String,
        @Query("with") with :String
    ) : Response<GetAllproductsResponse>


    @GET("cart")
    suspend fun getAllProductsInCart(
        @Header("Authorization") token: String,
    ) : Response<GetCartResponse>

    @POST("cart/{productID}")
    suspend fun addProductToCart(
        @Header("Authorization") token: String,
        @Path("productID") productID :Int
    ):Response<Unit>

    @DELETE("cart")
    suspend fun deleteProductFromCart(
        @Header("Authorization") token: String,
        @Query("name") name :String
    ) :Response<Unit>



    @GET("favourite")
    suspend fun getAllProductsInFavourite(
        @Header("Authorization") token: String,
    ) : Response<GetAllProductsInFavorite>

    @POST("favourite/{productID}")
    suspend fun addProductToFavourite(
        @Header("Authorization") token: String,
        @Path("productID") productID :Int
    ):Response<Unit>

    @DELETE("favourite")
    suspend fun deleteProductFromFavourite(
        @Header("Authorization") token: String,
        @Query("name") name :String
    ) :Response<Unit>

}