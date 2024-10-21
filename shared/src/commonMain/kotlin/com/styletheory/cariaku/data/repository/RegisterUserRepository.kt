package com.styletheory.cariaku.data.repository

import com.styletheory.cariaku.data.model.request.RegisterUserRequest

/**
 * Created by Jessica Setyani on 21-10-2024.
 */
interface RegisterUserRepository {
    fun signUpUser(registerUserRequest: RegisterUserRequest)
}