package com.example.nytimesdesigncompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nytimesdesigncompose.R

@Composable
fun LogoHeader() {
    Image(
        painter = painterResource(id = R.drawable.prew_icon),
        contentDescription = "Лого",
        modifier = Modifier.size(180.dp)
    )
}