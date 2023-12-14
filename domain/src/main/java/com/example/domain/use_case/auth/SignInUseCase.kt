package com.example.domain.use_case.auth

import com.example.domain.repo.AuthenticationRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
){
    suspend fun invoke(email :String ,password :String) = authenticationRepository.signIn(email,password)
}