package com.example.myapplication.data.service


import com.example.myapplication.data.model.CreateUser
import com.example.myapplication.data.response.auth.SignInNetworkResponse
import com.example.myapplication.data.response.auth.SignOutNetworkResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationService {

    @POST("auth/login")
    suspend fun signIn(
        @Query("email") email: String,
        @Query("password") password: String
    ) : Response<SignInNetworkResponse>

    @POST("auth/logout")
    suspend fun signOut(
        @Header("Authorization") token: String
    ) : Response<SignOutNetworkResponse>

    @POST("auth/register")
    suspend fun register(
        @Body request: CreateUser
    ) : Response<SignInNetworkResponse>

}

