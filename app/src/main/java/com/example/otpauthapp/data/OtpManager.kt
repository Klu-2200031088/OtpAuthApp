package com.example.otpauthapp.data

import timber.log.Timber

class OtpManager {

    private val otpMap = mutableMapOf<String, OtpData>()
    private val expiryTime = 60_000L

    fun generateOtp(email: String): String {
        val otp = (100000..999999).random().toString()

        otpMap[email] = OtpData(
            otp = otp,
            createdAt = System.currentTimeMillis(),
            attemptsLeft = 3
        )

        // 🔴 TEMPORARY LOG FOR TESTING
        Timber.d("OTP for $email is $otp")

        return otp
    }

    fun validateOtp(email: String, inputOtp: String): Boolean {
        val data = otpMap[email] ?: return false

        if (System.currentTimeMillis() - data.createdAt > expiryTime) {
            otpMap.remove(email)
            return false
        }

        if (data.otp == inputOtp) {
            otpMap.remove(email)
            return true
        }

        otpMap[email] = data.copy(attemptsLeft = data.attemptsLeft - 1)
        return false
    }
}
