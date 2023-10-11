package com.ug.route.ui.design_matrials.text

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ug.route.R
import com.ug.route.ui.theme.DarkPurple

@SuppressLint("UnrememberedMutableState", "StateFlowValueCalledInComposition")
@Composable
fun AccountField(
    modifier: Modifier = Modifier,
    value : String,
    text : String,
    hint : String,
    isError: Boolean,
    onValueChange : (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    visibility : Boolean = true,
    onClickVisibilityIcon : () -> Unit = {},
    onChangePasswordVisibility : (Boolean) -> Int = { 0 },
    errorMessage : String = ""
){

    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current
    var isTextFieldEnabled by remember { mutableStateOf(false) }
    val textFieldValue = remember(value) {
        mutableStateOf(TextFieldValue(value, TextRange(value.length)))
    }


    LaunchedEffect(isTextFieldEnabled) {
        if (isTextFieldEnabled) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = modifier.padding(horizontal = 16.dp)
    ){

        Text18(
            text = text,
            color = DarkPurple
        )


        OutlinedTextField(
            enabled = if (isError) true else isTextFieldEnabled,
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
                        color = DarkPurple,
                        textAlign = TextAlign.Center,
                    )
                )
            },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .height(64.dp),
            shape = RoundedCornerShape(16.dp),
            value = textFieldValue.value,
            onValueChange = {
                textFieldValue.value = TextFieldValue(
                    it.text,
                    selection = it.selection
                )
                onValueChange(it.text)
            },
            trailingIcon = {

                if (isFocused){

                    if (visualTransformation == VisualTransformation.None){

                        IconButton(onClick = {
                            focusManager.clearFocus()
                            isTextFieldEnabled = false
                        }) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                tint = DarkPurple,
                                imageVector = Icons.Default.Done,
                                contentDescription = ""
                            )
                        }

                    } else {

                        Row {

                            IconButton(onClick =  onClickVisibilityIcon) {
                                Icon(
                                    modifier = Modifier.size(32.dp),
                                    tint = DarkPurple,
                                    painter = painterResource(id = onChangePasswordVisibility(visibility)),
                                    contentDescription = "")
                            }

                            IconButton(onClick = {
                                focusManager.clearFocus()
                                isTextFieldEnabled = false
                            }) {
                                Icon(
                                    modifier = Modifier.size(32.dp),
                                    tint = DarkPurple,
                                    imageVector = Icons.Default.Done,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }

                else {

                    IconButton(onClick = {
                        isTextFieldEnabled = true
                        focusRequester.requestFocus()
                    }) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            tint = DarkPurple,
                            painter = painterResource(id = R.drawable.edit),
                            contentDescription = ""
                        )
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                cursorColor = DarkPurple,
                focusedBorderColor = DarkPurple,
                errorContainerColor = Color.White,
                unfocusedBorderColor = Color(0x4D004182)
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(300)
            ),
            visualTransformation = if (visibility)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
        )

        Text(
            text = errorMessage,
            color = Color.Red,
            modifier = Modifier
                .padding(top = 8.dp)
                .alpha(if (isError) 1f else 0f)
        )
    }
}