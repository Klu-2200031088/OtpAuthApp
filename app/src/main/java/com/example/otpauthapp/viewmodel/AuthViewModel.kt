package com.example.otpauthapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.otpauthapp.data.OtpManager
import timber.log.Timber

class AuthViewModel : ViewModel() {

    private val otpManager = OtpManager()

    val authState = mutableStateOf<AuthState>(AuthState.Login)

    fun sendOtp(email: String) {
        val otp = otpManager.generateOtp(email)
        authState.value = AuthState.Otp(email, otp)
    }

    fun verifyOtp(email: String, otp: String) {
        val success = otpManager.validateOtp(email, otp)

        if (success) {
            Timber.d("OTP validation success")
            authState.value = AuthState.Session(System.currentTimeMillis())
        } else {
            Timber.d("OTP validation failure")
            authState.value = AuthState.Error("Invalid or expired OTP")
        }
    }

    fun logout() {
        Timber.d("Logout")
        authState.value = AuthState.Login
    }
}
