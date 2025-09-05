package com.example.nytimesdesigncompose.ui.components

import android.view.MotionEvent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginButton(
    isEnabled: Boolean = false,
    onClick: () -> Unit = {},
) {
    var isPressed by remember { mutableStateOf(false) }
    Button(
        onClick = {
            onClick()
            isPressed = false
        },
        enabled = isEnabled,
        modifier = Modifier
            .height(48.dp)
            .width(112.dp)
            .pointerInteropFilter {
            if (it.action == MotionEvent.ACTION_DOWN && isEnabled) {
                isPressed = true
            }
            if (it.action == MotionEvent.ACTION_UP || it.action == MotionEvent.ACTION_CANCEL) {
                isPressed = false
            }
            false
        },
        shape = RoundedCornerShape(2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled){
                if(isPressed){
                    Color.LightGray
                }
                else{
                    Color.White
                }

            } else {
                Color.Gray
            },
            disabledContainerColor = Color.Gray,
        )
    ) {
        Text("ВОЙТИ", fontSize = 18.sp, color = Color.DarkGray)
    }

}