package com.example.wealthai.presentation.auth.login.login_state

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = ""
)
