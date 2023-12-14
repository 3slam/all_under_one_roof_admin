package com.example.domain.use_case.auth

import com.example.domain.repo.AuthenticationRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
){
    suspend fun invoke(name :  String , phone :String , email :String ,password :String) =
        authenticationRepository.register(name ,phone, email,password)
}