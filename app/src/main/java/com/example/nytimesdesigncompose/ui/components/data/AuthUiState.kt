package com.example.nytimesdesigncompose.ui.components.data

data class AuthUiState(
    val login: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val loginError: String = "",
    val passwordError: String = "",
    val isFormValid: Boolean = false,
    val authSuccess: String = "",
    val authLoginError: String = "",
    val authPasswordError: String = "",
)