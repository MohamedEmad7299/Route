package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    hint : String,
    modifier: Modifier = Modifier,
    value : String,
    onValueChange : (String) -> Unit,
    passwordVisibility : Boolean,
    onClickVisibilityIcon : () -> Unit,
    onChangePasswordVisibility : (Boolean) -> Int,
    isError : Boolean = false
){

    OutlinedTextField(
        isError = isError,
        singleLine = true,
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(300),
                    color = Color(0xB2000000),
                    textAlign = TextAlign.Center,
                )
            )
        },
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            IconButton(onClick =  onClickVisibilityIcon) {
                Icon(painter = painterResource(id = onChangePasswordVisibility(passwordVisibility)),
                    contentDescription = "")
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            cursorColor = DarkBlue,
            focusedBorderColor = DarkBlue
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontWeight = FontWeight(300)
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = if (passwordVisibility)
            VisualTransformation.None
        else
            PasswordVisualTransformation()
    )
}