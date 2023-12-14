package com.example.myapplication.data.repo

import android.util.Log
import com.example.myapplication.data.model.CreateUser
import com.example.myapplication.data.service.AuthenticationService


import javax.inject.Inject

class AuthenticationRepositoryImp @Inject constructor(
 private val authenticationService: AuthenticationService
) : AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): String  {
        return try {
            val result = authenticationService.signIn(email, password)
            Log.d("auth--SignIn",result.body().toString())
              result.body()!!.api_token
        }catch (e:Exception) {
            throw Exception("make sure the email and password are correct")
        }
    }

    override suspend fun signOut(token :String): Boolean {
        return try {
            val result = authenticationService.signOut("Bearer $token")
            Log.d("auth--SignOut",result.body().toString())
            return true
        }catch (e:Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String
    ): String {
        return try {
            val result = authenticationService.register(CreateUser(name,email,phone,password))
            Log.d("auth--register",result.code().toString())
            result.body()!!.api_token
        }catch (e:Exception) {
            throw Exception("Make sure the phone or the mail does not used before")
        }
    }
}