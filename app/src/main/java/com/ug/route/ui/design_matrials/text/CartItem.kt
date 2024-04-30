package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ug.route.R
import com.ug.route.ui.theme.CardStrokeColor
import com.ug.route.ui.theme.DarkPurple
import java.text.NumberFormat
import java.util.Locale


@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    itemName: String,
    imageResource: Int,
    circleColor: Color,
    colorName: String,
    itemPrice: Int,
    count : Int,
    onClickAdd: () -> Unit,
    onClickDelete: () -> Unit,
    onClickMinus: () -> Unit
){

    Card(
        modifier = modifier
            .padding(vertical = 8.dp)
            .height(120.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(size = 16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = CardStrokeColor
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        ConstraintLayout(
            Modifier.fillMaxSize()
        ){

            val (
                image,
                name,
                circle,
                color,
                price,
                deleteButton,
                addAndMinusButtons) = createRefs()

            Card(
                modifier = Modifier
                    .width(120.dp)
                    .padding(end = 8.dp)
                    .fillMaxHeight()
                    .constrainAs(image)
                    {
                        start.linkTo(parent.start)
                    },
                shape = RoundedCornerShape(size = 16.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = CardStrokeColor
                )
            ){
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = painterResource(id = imageResource),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 128.dp,end = 48.dp)
                    .constrainAs(name)
                    {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top,8.dp)
                    },
                text = itemName,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = DarkPurple,
                    textAlign = TextAlign.Start
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Canvas(
                modifier = Modifier
                    .size(16.dp)
                    .clip(shape = CircleShape)
                    .background(color = circleColor)
                    .constrainAs(circle)
                    {
                        start.linkTo(image.end, 8.dp)
                        top.linkTo(name.bottom, 8.dp)
                    }
            ){

            }

            Text(
                modifier = Modifier.constrainAs(color)
                {
                    start.linkTo(circle.end,8.dp)
                    top.linkTo(circle.top)
                    bottom.linkTo(circle.bottom)
                },
                text = colorName,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = DarkPurple,
                    textAlign = TextAlign.Center,
                )
            )

            Text(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .constrainAs(price)
                    {
                        start.linkTo(image.end, 8.dp)
                        top.linkTo(color.bottom, 8.dp)
                    },
                text = "EGP ${NumberFormat.getNumberInstance(Locale.US).format(itemPrice)}",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(500),
                    color = DarkPurple,
                    textAlign = TextAlign.Center,
                )
            )

            IconButton(
                modifier = Modifier.constrainAs(deleteButton)
                {
                    end.linkTo(parent.end,8.dp)
                    top.linkTo(parent.top,8.dp)
                },
                onClick = onClickDelete,
                colors= IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                )
            ){
                Icon(
                    tint = DarkPurple,
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete icon"
                )
            }

            AddAndMinusButtons(
                modifier = Modifier.constrainAs(addAndMinusButtons){
                    end.linkTo(parent.end, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                },
                onClickMinus = onClickMinus,
                value = count.toString(),
                onClickAdd = onClickAdd)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CartItemPreview(){

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        CartItem(
            Modifier,
            "koraa",
            0,
            Color.DarkGray,
            "Gray",
            1200,
            1,
            {},
            {},
            {}
        )
    }
}