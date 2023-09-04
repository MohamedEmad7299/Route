package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue

@Composable
fun SearchBarAndCart(
    modifier: Modifier = Modifier,
    isError: Boolean,
    value: String,
    onValueChange : (String) -> Unit,
    onClickCartIcon : () -> Unit
){

    Row(
        modifier
            .padding(16.dp)
            .fillMaxWidth(),
        Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            isError = isError,
            singleLine = true,
            placeholder = {
                Text(
                    text = "what do you search for?",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(300),
                        color = DarkBlue,
                        textAlign = TextAlign.Center,
                    )
                )
            },
            modifier = Modifier
                .width(300.dp),
            shape = RoundedCornerShape(32.dp),
            value = value,
            onValueChange = onValueChange,
            trailingIcon = {
                IconButton(onClick =  {}) {
                    Icon(
                        tint = DarkBlue,
                        painter = painterResource(id = R.drawable.baseline_search_24),
                        contentDescription = "search icon")
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                cursorColor = DarkBlue,
                focusedBorderColor = DarkBlue,
                errorContainerColor = Color.White,
                unfocusedBorderColor = DarkBlue
            ),
            textStyle = TextStyle(
                color = DarkBlue,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300)
            )
        )


        IconButton(
            onClick =  onClickCartIcon,
            modifier = Modifier.size(50.dp)
        ){
            Icon(
                tint = DarkBlue,
                painter = painterResource(id = R.drawable.cart),
                contentDescription = "cart icon")
        }
    }
}