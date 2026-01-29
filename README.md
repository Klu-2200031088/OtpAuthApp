# OtpAuthApp
Android Assignment(Lokal App-Hiring- Assignment for Android Internship)

🔐 Passwordless Email + OTP Authentication (Android)
📱 Overview

This project implements a passwordless authentication flow using Email + OTP, built entirely on the client side using Kotlin, Jetpack Compose, ViewModel, and Coroutines.

The app allows a user to:

Enter an email address

Generate and verify a one-time password (OTP)

Log in without a password

View a session screen with a live session timer

Logout safely

All logic is implemented locally, as required. No backend or external authentication service is used.

🛠 Tech Stack

Language: Kotlin

UI: Jetpack Compose

Architecture: ViewModel + UI State (one-way data flow)

Async: Kotlin Coroutines

Analytics / Logging: Timber

IDE: Android Studio

🧩 Project Structure
ui/
 ├── LoginScreen.kt
 ├── OtpScreen.kt
 ├── SessionScreen.kt

viewmodel/
 ├── AuthViewModel.kt
 ├── AuthState.kt

data/
 ├── OtpManager.kt

analytics/
 ├── AnalyticsLogger.kt

🔑 OTP Logic & Expiry Handling
OTP Generation

A 6-digit numeric OTP is generated locally using a random number generator.

OTPs are generated when the user taps Send OTP.

OTP Storage

OTPs are stored per email address using a Map<String, OtpData>.

Each email maps to an OtpData object containing:

otp – the generated OTP

createdAt – timestamp of generation

attemptsLeft – remaining validation attempts

OTP Rules Implemented

OTP length: 6 digits

OTP expiry: 60 seconds

Maximum attempts: 3

Resend OTP:

Invalidates the previous OTP

Resets attempt count to 3

Overwrites existing OTP data for that email

OTP Validation

OTP is considered invalid if:

It has expired (current time − creation time > 60 seconds)

The entered OTP is incorrect

The maximum number of attempts is exceeded

Successful validation clears OTP data for the email.

🧠 Data Structures Used & Reasoning
Map<String, OtpData>

Key: Email address

Value: OTP metadata (OtpData)

Why this approach?

Allows storing OTPs per user/email

Enables quick lookup and invalidation

Prevents global or shared mutable state

Scales cleanly if multiple users are simulated

OtpData (data class)

Encapsulates all OTP-related state in one place:

OTP value

Expiry timestamp

Remaining attempts

This keeps business logic isolated and testable.

⏱ Session Screen & Timer Logic

After successful OTP verification:

The session start time is recorded in the ViewModel.

A live session duration timer (mm:ss) is displayed.

Timer Behavior

Implemented using LaunchedEffect

Updates every second

Survives recompositions

Stops immediately on logout

Logout resets authentication state and returns the user to the login screen.

📊 External SDK Integration (Timber)
Why Timber?

Lightweight

Ideal for structured debug logging

Commonly used in Android projects

Requires no backend setup

Integration Details

Timber dependency added via Gradle

Initialized in the Application class using Timber.DebugTree()

Logged Events

The following events are logged:

OTP generated

OTP validation success

OTP validation failure

Logout action

This demonstrates intentional SDK integration and event tracking.

🎨 Jetpack Compose Usage

The project demonstrates:

@Composable functions for UI

remember and rememberSaveable for state

LaunchedEffect for side effects (timer)

State hoisting from ViewModel

Proper recomposition handling

UI logic is kept separate from business logic.

🧱 Architecture & State Management

ViewModel manages all business logic and state

UI observes state and reacts accordingly

One-way data flow is maintained

No global mutable state is used

No blocking calls on the main thread

⚠️ Edge Cases Handled

Expired OTP

Incorrect OTP

Exceeded OTP attempts

Resending OTP

Screen rotation without state loss

🤖 AI Tool Usage Disclosure

GPT was used as a supporting tool for:

Clarifying Android/Compose concepts

Debugging errors

Reviewing logic flow

All core logic, architectural decisions, and implementation were understood and implemented by me.
AI assistance was used responsibly, in line with the assignment guidelines.

▶️ Setup Instructions

Clone the repository

Open in Android Studio

Sync Gradle

Run on emulator or physical device (API 24+)

No additional setup or backend configuration is required.

✅ Assignment Compliance Summary

✔ Local OTP generation & validation
✔ OTP expiry & attempts enforced
✔ ViewModel + UI State architecture
✔ Jetpack Compose best practices
✔ External SDK integration (Timber)
✔ Clear documentation

🏁 Conclusion

This project demonstrates a clean, maintainable implementation of a passwordless authentication flow using modern Android development practices, with a strong focus on state management, composable UI, and clarity of logic.
