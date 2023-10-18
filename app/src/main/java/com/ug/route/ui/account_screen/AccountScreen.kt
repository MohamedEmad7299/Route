package com.ug.route.ui.account_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ug.route.R
import com.ug.route.data.database.entities.UserEntity
import com.ug.route.ui.design_matrials.text.AccountField
import com.ug.route.ui.design_matrials.text.SmallLogo
import com.ug.route.ui.design_matrials.text.StandardButton
import com.ug.route.ui.design_matrials.text.Text18
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.utils.SharedPreferences

@Composable
fun AccountScreen(
    navController : NavController,
    viewModel: AccountViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()

    AccountContent(
        screenState,
        screenState.userEntity,
        onChangeName = viewModel::onChangeName,
        onChangeEmail = viewModel::onChangeEmail,
        onChangePassword = viewModel::onChangePassword,
        onChangePhone = viewModel::onChangePhone,
        onChangeAddress = viewModel::onChangeAddress,
        saveEdit = viewModel::saveEdit,
        onClickVisibilityIcon = viewModel::updatePasswordVisibility,
        onChangePasswordVisibility = viewModel::onChangeVisibility,
        onSignOut = {
            viewModel.signOut(navController)
        }
    )
}

@Composable
fun AccountContent(
    screenState: AccountState,
    userEntity : UserEntity,
    onChangeName : (String) -> Unit,
    onChangeEmail : (String) -> Unit,
    onChangePassword : (String) -> Unit,
    onChangePhone : (String) -> Unit,
    onChangeAddress : (String) -> Unit,
    saveEdit: () -> Unit,
    onClickVisibilityIcon :  () -> Unit,
    onChangePasswordVisibility :  (Boolean) -> Int,
    onSignOut: () -> Unit
) {

    val context = LocalContext.current

    if (screenState.message.isNotEmpty()){
        LaunchedEffect(key1 = screenState.launchedEffectKey){
            Toast.makeText(context, screenState.message, Toast.LENGTH_SHORT).show()
        }
    }

    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ){
        item{

            ConstraintLayout{

                val (
                    logo,
                    welcomeText,
                    email,
                    yourFullName,
                    yourEmail,
                    yourPassword,
                    yourPhone,
                    yourAddress,
                    saveButton,
                    signOut) = createRefs()

                SmallLogo(
                    modifier = Modifier
                        .constrainAs(logo){
                            start.linkTo(parent.start,16.dp)
                            top.linkTo(parent.top,16.dp)
                        }
                )
                
                Text18(
                    text = if (screenState.fullName.isEmpty()) "Welcome" else stringResource(R.string.welcome, screenState.fullName.split(" ")[0]),
                    modifier = Modifier.constrainAs(welcomeText){
                        start.linkTo(parent.start,16.dp)
                        top.linkTo(logo.bottom,24.dp)
                    },
                    color = DarkPurple
                )

                Text(
                    modifier = Modifier.constrainAs(email){
                        start.linkTo(parent.start,16.dp)
                        top.linkTo(welcomeText.bottom)
                    },
                    text = SharedPreferences.loggedEmail?:"",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = DarkPurple,
                        textAlign = TextAlign.Center,
                    )
                )

                AccountField(
                    modifier = Modifier
                        .constrainAs(yourFullName){
                            start.linkTo(parent.start)
                            top.linkTo(email.bottom,40.dp)
                        },
                    value = userEntity.name,
                    text = stringResource(R.string.full_name),
                    hint = stringResource(R.string.name_hint),
                    isError = screenState.isNameError,
                    onValueChange = onChangeName,
                    errorMessage = stringResource(R.string.name_error_message)
                )

                AccountField(
                    modifier = Modifier
                        .constrainAs(yourEmail){
                            start.linkTo(parent.start)
                            top.linkTo(yourFullName.bottom,8.dp)
                        },
                    value = userEntity.email,
                    hint = stringResource(R.string.email_hint),
                    text = stringResource(R.string.e_mail),
                    isError = screenState.isEmailError,
                    onValueChange = onChangeEmail,
                    errorMessage = stringResource(R.string.email_error_message)
                )

                AccountField(
                    modifier = Modifier
                        .constrainAs(yourPassword){
                            start.linkTo(parent.start)
                            top.linkTo(yourEmail.bottom,8.dp)
                        },
                    value = userEntity.password,
                    text = stringResource(R.string.password),
                    hint = stringResource(R.string.password_hint),
                    isError = screenState.isPasswordError,
                    onValueChange = onChangePassword,
                    visualTransformation = PasswordVisualTransformation(),
                    visibility = screenState.visibility,
                    onChangePasswordVisibility = onChangePasswordVisibility,
                    onClickVisibilityIcon = onClickVisibilityIcon,
                    errorMessage = stringResource(R.string.password_error_message)
                )

                AccountField(
                    modifier = Modifier
                        .constrainAs(yourPhone){
                            start.linkTo(parent.start)
                            top.linkTo(yourPassword.bottom,8.dp)
                        },
                    value = userEntity.phone,
                    hint = stringResource(R.string.mobile_number_hint),
                    text = stringResource(R.string.mobile_number),
                    isError = screenState.isPhoneError,
                    onValueChange = onChangePhone,
                    errorMessage = stringResource(R.string.mobile_number_error_message)
                )

                AccountField(
                    modifier = Modifier
                        .constrainAs(yourAddress){
                            start.linkTo(parent.start)
                            top.linkTo(yourPhone.bottom,8.dp)
                        },
                    value = userEntity.address,
                    text = stringResource(R.string.address),
                    hint = stringResource(R.string.address_hint),
                    onValueChange = onChangeAddress,
                    isError = false
                )

                StandardButton(
                    modifier = Modifier
                        .constrainAs(saveButton){
                        top.linkTo(yourAddress.bottom,32.dp)
                    },
                    onClick = saveEdit,
                    buttonColor = DarkBlue
                ) {

                    if (screenState.isLoading){

                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            color = DarkBlue,
                            strokeWidth = 5.dp
                        )

                    } else {

                        Text(
                            text = stringResource(R.string.save),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontWeight = FontWeight(600),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }

                ClickableText(
                    text = AnnotatedString(stringResource(R.string.sign_out)),
                    modifier = Modifier
                        .padding(bottom = 80.dp)
                        .constrainAs(signOut) {
                            top.linkTo(saveButton.bottom, 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(600),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline
                    )
                ){
                    onSignOut()
                }
            }
        }
    }
}