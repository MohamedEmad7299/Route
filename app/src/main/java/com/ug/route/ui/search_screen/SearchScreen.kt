package com.ug.route.ui.search_screen

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ug.route.R
import com.ug.route.ui.home_screen.HomeState
import com.ug.route.ui.home_screen.HomeViewModel
import com.ug.route.ui.theme.DarkBlue

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()

    SearchContent(
        screenState = screenState,
        onSearch = viewModel::onSearch,
        onQueryChange = viewModel::onQueryChange,
        onActiveChange = {
            viewModel.onActiveChange(it)
            if(!it) navController.popBackStack()
        },
        onClickClose = viewModel::onClickClose,
        backToHome = { navController.popBackStack() }
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    screenState : HomeState,
    onSearch :(String) -> Unit,
    onQueryChange : (String) -> Unit,
    onActiveChange :(Boolean) -> Unit,
    onClickClose : () -> Unit,
    backToHome : () -> Unit,
){

    val focusRequester = remember { FocusRequester() }
    val currentView = LocalView.current

    LaunchedEffect(screenState.isSearchBarActive){

        focusRequester.requestFocus()
    }

    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ){

        SearchBar(
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            query = screenState.query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            active = screenState.isSearchBarActive,
            onActiveChange = onActiveChange,
            leadingIcon = {

                IconButton(onClick = {

                    hideKeyboard(currentView)
                    backToHome()

                }) {
                    Icon(
                        tint = DarkBlue,
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "")
                }
            },
            trailingIcon = if (screenState.query.isNotEmpty()){
                {
                    IconButton(onClick = onClickClose) {
                        Icon(
                            tint = DarkBlue,
                            imageVector = Icons.Filled.Close,
                            contentDescription = "")
                    }
                }
            } else null,
            placeholder = {

                Column(
                    Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "what do you search for?",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            fontWeight = FontWeight(300),
                            color = DarkBlue,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            },
            colors = SearchBarDefaults.colors(
                containerColor = Color.White,
                inputFieldColors = SearchBarDefaults.inputFieldColors(
                    cursorColor = DarkBlue,
                )
            )
        ){
            screenState.history.reversed().forEach {

                Row(
                    Modifier
                        .clickable { onQueryChange(it) }
                        .fillMaxWidth()
                        .padding(16.dp)
                ){
                    Icon(
                        modifier = Modifier.padding(end = 8.dp),
                        tint = DarkBlue,
                        imageVector = Icons.Default.History,
                        contentDescription = ""
                    )

                    Text(text = it)
                }
            }
        }
    }
}

fun hideKeyboard(view: View) {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}