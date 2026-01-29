package com.example.otpauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.otpauthapp.ui.LoginScreen
import com.example.otpauthapp.ui.OtpScreen
import com.example.otpauthapp.ui.SessionScreen
import com.example.otpauthapp.ui.theme.OtpAuthAppTheme
import com.example.otpauthapp.viewmodel.AuthState
import com.example.otpauthapp.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            OtpAuthAppTheme {

                val authViewModel: AuthViewModel = viewModel()
                val authState by authViewModel.authState

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { _ ->

                when (val state = authState) {
                        is AuthState.Login ->
                            LoginScreen(authViewModel)
                        is AuthState.Otp ->
                            OtpScreen(
                                email = state.email,
                                otp = state.otp,
                                viewModel = authViewModel
                            )
                        is AuthState.Session ->
                            SessionScreen(state.startTime, authViewModel)
                        is AuthState.Error ->
                            LoginScreen(authViewModel)
                    }
                }

            }
        }
    }
}
