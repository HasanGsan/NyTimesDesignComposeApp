package com.example.nytimesdesigncompose.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.text.matches

class AuthViewModel : ViewModel() {

    private val validTrueLogin = "Логин_Юзера"
    private val validTruePassword = "1234pass"

    //Состояния которые меняет Вьюмоделька
    private val _login = MutableStateFlow("")

    private val _password = MutableStateFlow("")
    private val _passwordVisible = MutableStateFlow(false)

    private val _loginError = MutableStateFlow("")
    private val _passwordError = MutableStateFlow("")

    private val _isFormValid = MutableStateFlow(false)

    private val _authSuccess = MutableStateFlow("")
    private val _authLoginError = MutableStateFlow("")
    private val _authPasswordError = MutableStateFlow("")

    //Состояния которые читает юайка
    val login: StateFlow<String> = _login.asStateFlow()

    val password: StateFlow<String> = _password.asStateFlow()
    val passwordVisible: StateFlow<Boolean> = _passwordVisible.asStateFlow()

    val loginError: StateFlow<String> = _loginError.asStateFlow()
    val passwordError: StateFlow<String> = _passwordError.asStateFlow()

    val isFormValid: StateFlow<Boolean> = _isFormValid.asStateFlow()

    val authSuccess: StateFlow<String> = _authSuccess.asStateFlow()
    val authLoginError: StateFlow<String> = _authLoginError.asStateFlow()
    val authPasswordError: StateFlow<String> = _authPasswordError.asStateFlow()

    fun updateLogin(newLogin: String){
        _login.value = newLogin
        _loginError.value = validateLogin(newLogin)
        _authSuccess.value = ""
        updateFormValidity()
    }

    fun updatePassword(newPassword: String){
        _password.value = newPassword
        _passwordError.value = validatePassword(newPassword)
        _authSuccess.value = ""
        updateFormValidity()
    }

    fun togglePasswordVisibility(){
        _passwordVisible.value = !_passwordVisible.value
    }

    fun trueAuth() {
        _authSuccess.value = ""
        _authLoginError.value = ""
        _authPasswordError.value = ""
        if(_isFormValid.value){
            if(_login.value == validTrueLogin && _password.value != validTruePassword){
                _authPasswordError.value = "Пароль введен неверно!"
            }
            else if(_login.value != validTrueLogin && _password.value == validTruePassword){
                _authLoginError.value = "Логин введен неверно!"
            }
            else if(_login.value == validTrueLogin && _password.value == validTruePassword){
                _authSuccess.value = "Данные введены верно!"
            }
            else{
                _authSuccess.value = "Таких данных не существует!"
            }
        }
    }

    private fun validateLogin(login: String) : String {
        if(login.isEmpty()){
            return "Поле не может быть пустым!"
        }

        if(!login.matches(Regex("[а-яёА-ЯЁ_]+"))){
            return "Логин должен содержать только кириллицу!"
        }

        return ""


    }

    private fun validatePassword(password: String): String{
        if(password.isEmpty()){
            return "Поле не может быть пустым!"
        }

        if(password.length < 6){
            return "Пароль должен содержать минимум 6 символов!"
        }

        if(password.length > 12){
            return "Пароль должен содержать максимум 12 символов!"
        }

        if(!password.matches(Regex(".*\\d.*"))){
            return "Пароль должен содержать минимум 1 цифру!"
        }

        if(!password.matches(Regex(".*[a-zA-Z].*"))){
            return "Пароль должен содержать минимум 1 латинскую букву!"
        }

        if (password.matches(Regex(".*[а-яёА-ЯЁ].*"))) {
            return "Пароль должен содержать только цифры и латинские буквы!"
        }

        return ""

    }

    private fun updateFormValidity() {
        val loginValid = _login.value.isNotEmpty() && _loginError.value.isEmpty()
        val passwordValid = _password.value.isNotEmpty() && _passwordError.value.isEmpty()
        _isFormValid.value = loginValid && passwordValid
    }






}