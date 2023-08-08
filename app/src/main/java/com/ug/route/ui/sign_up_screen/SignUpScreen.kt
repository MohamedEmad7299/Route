package com.ug.route.ui.sign_up_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ug.route.ui.theme.DarkBlue


@Composable
fun SignUpScreen(){

    SignUpContent()
}

@Preview(showSystemUi = true)
@Composable
fun SignUpContent() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ){
        item {


            ConstraintLayout(

            ) {

                val (txt1,txt2) = createRefs()

                Text(
                    text = "UG1",
                    fontSize = 64.sp,
                    color = DarkBlue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(txt1){
                        top.linkTo(parent.top,64.dp)
                    }
                )

                Text(
                    text = "UG2",
                    fontSize = 64.sp,
                    color = DarkBlue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(txt2){
                            top.linkTo(txt1.bottom,600.dp)
                        }
                )
            }
        }
    }
}
