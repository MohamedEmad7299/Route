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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
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
import com.ug.route.data.fake.FakeData
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.utils.Screen
import com.ug.route.utils.handelInternetError

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
){

    val screenState by viewModel.screenState.collectAsState()
    val context = LocalContext.current

    SearchContent(
        screenState = screenState,
        onSearch = { query ->
            handelInternetError(
                context,
                {navController.navigate("${Screen.ProductsScreen.route}/${query}")},
                {navController.navigate(Screen.NoInternetScreen.route)}
            )
        },
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
    screenState : SearchState,
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
                        text = stringResource(R.string.what_do_you_search_for),
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

            val productsNames = if (screenState.query.isBlank()) FakeData.products.map { it.title }.distinct() else screenState.matchSearchQuery().distinct()

            LazyColumn{

                items(productsNames){ productName ->

                    Row(
                        modifier = Modifier
                            .padding(horizontal =  16.dp , vertical = 8.dp)
                            .height(32.dp)
                            .fillMaxWidth()
                            .clickable {
                                hideKeyboard(currentView)
                                onSearch(productName!!)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(text = productName!!)
                    }
                }
            }
        }
    }
}

fun hideKeyboard(view: View) {
    val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}