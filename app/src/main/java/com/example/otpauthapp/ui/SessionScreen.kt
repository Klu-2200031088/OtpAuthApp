package com.example.otpauthapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.otpauthapp.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SessionScreen(startTime: Long, viewModel: AuthViewModel) {
    var seconds by remember { mutableStateOf(0L) }

    LaunchedEffect(Unit) {
        while (true) {
            seconds = (System.currentTimeMillis() - startTime) / 1000
            delay(1000)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Session Duration: ${seconds / 60}:${seconds % 60}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { viewModel.logout() }) {
            Text("Logout")
        }
    }
}
