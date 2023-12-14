package com.example.domain.repo

interface AuthenticationRepository {
    suspend fun signIn(email :String ,password :String) :String
    suspend fun signOut(token :String) :Boolean
    suspend fun register(name: String, phone : String ,email :String, password: String) : String
}