package com.example.nytimesdesigncompose.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nytimesdesigncompose.LoginScreen
import com.example.nytimesdesigncompose.ui.components.animationDesign.AnimatedLoadingSpinner
import kotlinx.coroutines.delay

@Composable
fun AppScreen(innerPadding: PaddingValues = PaddingValues(0.dp)) {

    var hasShownSplash by rememberSaveable { mutableStateOf(false) }
    var showLoadingScreen by remember { mutableStateOf(!hasShownSplash) }

    LaunchedEffect(showLoadingScreen) {
        if(showLoadingScreen){
            delay(4000L)
            hasShownSplash = true
            showLoadingScreen = false
        }
    }

    if (showLoadingScreen) {
        AppLoadingScreen()
    } else {
        LoginScreen()
    }
}

@Composable
fun AppLoadingScreen(innerPadding: PaddingValues = PaddingValues(0.dp)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedLoadingSpinner(
                size = 64.dp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "NYTIMES",
                color = Color.White,
                fontSize = 48.sp,
            )
        }

        Spacer(modifier = Modifier.weight(1f))


        Text(
            text = "1.0.41",
            color = Color.Gray,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}
