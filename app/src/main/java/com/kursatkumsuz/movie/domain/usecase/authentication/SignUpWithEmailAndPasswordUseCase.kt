package com.kursatkumsuz.movie.domain.usecase.authentication

import com.kursatkumsuz.movie.domain.repository.AuthenticationRepository
import com.kursatkumsuz.movie.util.Response
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class SignUpWithEmailAndPasswordUseCase (
    private val authRepository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String) = flow {
        try {
            emit(Response.Loading)
            emit(Response.Success(authRepository.signUpWithEmailAndPassword(email, password).await()))
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage ?: "Unexpected Error!"))
        }
    }
}