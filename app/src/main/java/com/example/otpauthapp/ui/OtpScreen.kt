package com.example.otpauthapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.otpauthapp.viewmodel.AuthViewModel

@Composable
fun OtpScreen(
    email: String,
    otp: String,              // 👈 OTP now visible
    viewModel: AuthViewModel
) {
    var enteredOtp by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("OTP sent to $email")

        Spacer(modifier = Modifier.height(8.dp))

        // 🔴 TEMPORARY OTP DISPLAY (FOR TESTING)
        Text(
            text = "DEBUG OTP: $otp",
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = enteredOtp,
            onValueChange = { enteredOtp = it },
            label = { Text("Enter OTP") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.verifyOtp(email, enteredOtp) },
            enabled = enteredOtp.length == 6
        ) {
            Text("Verify OTP")
        }
    }
}
