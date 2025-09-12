package com.example.nytimesdesigncompose.ui.viewmodel


import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import com.example.nytimesdesigncompose.ui.components.data.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.text.matches

import com.example.nytimesdesigncompose.R


class AuthViewModel : ViewModel() {

    //Тестовые данные
    private val validTrueLogin = "Логин_Юзера"
    private val validTruePassword = "1234pass"

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun updateLogin(newLogin: String){
        _uiState.value = uiState.value.copy(
            login = newLogin,
            loginError = validateLogin(newLogin),
            authSuccess = ""
        )
        updateFormValidity()
    }

    fun updatePassword(newPassword: String){
        _uiState.value = uiState.value.copy(
            password = newPassword,
            passwordError = validatePassword(newPassword),
            authSuccess = ""
        )
        updateFormValidity()
    }

    fun togglePasswordVisibility(){
        _uiState.value = uiState.value.copy(
            passwordVisible = uiState.value.passwordVisible
        )
    }

    fun trueAuth() {
        if(uiState.value.isFormValid){
            val passwordError =  if (uiState.value.password != validTruePassword) "Неверный пароль" else ""

            val loginError = if(uiState.value.login != validTrueLogin) "Неверный логин" else ""

            _uiState.value = uiState.value.copy (
                authSuccess = "",
                authLoginError = loginError,
                authPasswordError = passwordError,
            )
        }
        else {
            _uiState.value = uiState.value.copy (
                authSuccess = "",
                authLoginError = "",
                authPasswordError = "",
            )
        }
    }

    private fun validateLogin(login: String) : String {
        if(login.isEmpty()) return "Поле не может быть пустым"
        if(!login.matches(Regex("[а-яёА-ЯЁ_]+"))) return "Логин пользователя должен быть на кириллице"

        return ""
    }

    private fun validatePassword(password: String): String{
        if(password.isEmpty()) return "Поле не может быть пустым"

        if(password.length < 6) return "Пароль должен содержать не менее 6 символов"

        if(password.length > 12) return "Пароль должен содержать максимум 12 символов"

        if(!password.matches(Regex(".*\\d.*"))) return "Пароль должен содержать хотя бы одну цифру"

        if(!password.matches(Regex(".*[a-zA-Z].*"))) return "Пароль должен содержать хотя бы одну латинскую букву"

        if (password.matches(Regex(".*[а-яёА-ЯЁ].*"))) return "Пароль должен содержать только цифры и латинские буквы"

        return ""
    }

    private fun updateFormValidity() {

        val loginValid = uiState.value.login.isNotEmpty() && uiState.value.loginError.isEmpty()
        val passwordValid = uiState.value.password.isNotEmpty() && uiState.value.passwordError.isEmpty()
        _uiState.value = uiState.value.copy(
            isFormValid = loginValid && passwordValid
        )


    }
}