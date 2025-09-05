package com.example.nytimesdesigncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nytimesdesigncompose.ui.components.LoginButton
import com.example.nytimesdesigncompose.ui.components.LoginInput
import com.example.nytimesdesigncompose.ui.components.LogoHeader
import com.example.nytimesdesigncompose.ui.components.PasswordInput
import com.example.nytimesdesigncompose.ui.theme.BlackBackground
import com.example.nytimesdesigncompose.ui.theme.NyTimesDesignComposeTheme
import com.example.nytimesdesigncompose.ui.viewmodel.AuthViewModel
import androidx.compose.runtime.collectAsState
import com.example.nytimesdesigncompose.ui.screens.AppScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            NyTimesDesignComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppScreen(innerPadding = innerPadding)
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreen(){

    val viewModel = viewModel<AuthViewModel>()

//    var login by remember { mutableStateOf("") }
//    var password by remember { mutableStateOf("") }
//    var passwordVisible by remember { mutableStateOf(false) }

    //Состояния которые читает юайка
    val login by viewModel.login.collectAsState()
    val password by viewModel.password.collectAsState()
    val passwordVisible by viewModel.passwordVisible.collectAsState()
    val loginError by viewModel.loginError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val isFormValid by viewModel.isFormValid.collectAsState()
    val authSuccess by viewModel.authSuccess.collectAsState()
    val authLoginError by viewModel.authLoginError.collectAsState()
    val authPasswordError by viewModel.authPasswordError.collectAsState()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(BlackBackground)
            .padding(start = 20.dp, end = 20.dp)
    ) {

        Spacer(Modifier.height(32.dp))

        LogoHeader()

        Spacer(Modifier.height(32.dp))

        LoginInput(
            login = login,
            onLoginChange = { viewModel.updateLogin(it)},
            isError = loginError.isNotEmpty(),
            errorMessage = loginError,
            authMessage = authSuccess,
            errorAuthMessage = authLoginError
        )

        PasswordInput(
            password = password,
            passwordVisible = passwordVisible,
            onPasswordChange = { viewModel.updatePassword(it) },
            onVisibilityChange = { viewModel.togglePasswordVisibility() },
            isError = passwordError.isNotEmpty(),
            errorMessage = passwordError,
            authMessage = authSuccess,
            errorAuthMessage = authPasswordError
        )

        Spacer(Modifier.height(24.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            LoginButton( isEnabled = isFormValid,
                onClick = {
                println("=== ДЕБАГ КНОПКИ ===")
                println("isFormValid = $isFormValid")
                println("login = '$login'")
                println("password = '$password'")
                println("loginError = '$loginError'")
                println("passwordError = '$passwordError'")
                println("===================")

                viewModel.trueAuth()
            }

            )
        }


    }

}



