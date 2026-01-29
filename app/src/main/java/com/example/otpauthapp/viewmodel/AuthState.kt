package com.example.otpauthapp.viewmodel

sealed class AuthState {
    object Login : AuthState()
    data class Otp(
        val email: String,
        val otp: String
    ) : AuthState()

    data class Session(val startTime: Long) : AuthState()
    data class Error(val message: String) : AuthState()
}
