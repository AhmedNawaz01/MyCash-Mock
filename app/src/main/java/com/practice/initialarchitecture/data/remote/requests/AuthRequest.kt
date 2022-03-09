package com.practice.initialarchitecture.data.remote.requests

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthLoginRequest(
    val email: String,
    val password: String
)

@JsonClass(generateAdapter = true)
data class AuthRegistrationRequest(
    val email: String,
    val password: String
)
