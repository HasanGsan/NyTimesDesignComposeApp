package com.example.nytimesdesigncompose.ui.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nytimesdesigncompose.R


@Composable
fun LoginInput(
    login: String,
    onLoginChange: (String) -> Unit,
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    authMessage: String = "",
    errorAuthMessage: String = ""
) {
    Column {
        OutlinedTextField(
            value = login,
            onValueChange = onLoginChange,
            label = {Text(stringResource(id = R.string.loginInputLabel))},
            modifier = modifier
                .fillMaxWidth(),
            singleLine = true,

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                focusedLabelColor = Color(0xFF757575),
                unfocusedLabelColor = Color(0xFF757575),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = Color(0xFF757575),
                unfocusedBorderColor = Color.Transparent
            )
        )
        Divider(
            color = if(isError) Color.Red else Color.DarkGray,
            thickness = 1.dp
        )

        if(isError && errorMessage.isNotEmpty()){
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = modifier.padding(start = 16.dp)
            )
        } else if (authMessage.isNotEmpty() && errorAuthMessage.isEmpty()){
            Text(
                text = authMessage,
                color = Color.White,
                fontSize = 12.sp,
                modifier = modifier.padding(start = 16.dp)
            )
        } else {
            Text(
                text = errorAuthMessage,
                color = Color.Red,
                fontSize = 12.sp,
                modifier = modifier.padding(start = 16.dp)
            )
        }



    }
}