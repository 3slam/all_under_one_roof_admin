package com.example.domain.use_case.auth


import com.example.domain.repo.AuthenticationRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
){
    suspend fun invoke(token :String) = authenticationRepository.signOut(token)
}