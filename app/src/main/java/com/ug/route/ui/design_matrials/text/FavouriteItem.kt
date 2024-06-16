package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.ug.route.R
import com.ug.route.ui.theme.CardStrokeColor
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.DarkPurple
import java.text.NumberFormat
import java.util.Locale


@Composable
fun FavouriteItem(
    modifier: Modifier = Modifier,
    itemName: String,
    imageURL: String,
    circleColor: Color,
    colorName: String,
    itemPrice: Int,
    onClickAdd: () -> Unit,
    onClickFavButton: () -> Unit,
    onClickItem: () -> Unit
){

    Card(
        modifier = modifier
            .clickable {
                onClickItem()
            }
            .height(140.dp)
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
                oldPrice,
                favButton,
                addButton
            ) = createRefs()

            Card(
                modifier = Modifier
                    .width(140.dp)
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
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = imageURL,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                modifier = Modifier
                    .padding(start = 148.dp,end = 48.dp)
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


            Text(
                modifier = Modifier.constrainAs(oldPrice)
                {
                    start.linkTo(image.end,8.dp)
                    top.linkTo(price.bottom)
                },
                text = "EGP ${NumberFormat.getNumberInstance(Locale.US).format(itemPrice+300)}",
                style = TextStyle(
                    fontSize = 11.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontWeight = FontWeight(400),
                    color = DarkPurple,
                    textAlign = TextAlign.Center,
                    textDecoration = TextDecoration.LineThrough
                )
            )


            IconButton(
                modifier = Modifier.constrainAs(favButton)
                {
                    end.linkTo(parent.end,8.dp)
                    top.linkTo(parent.top,8.dp)
                },
                onClick = onClickFavButton,
                colors= IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                )
            ){
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.fav),
                    contentDescription = "")
            }

            Button(
                onClick = onClickAdd,
                modifier = Modifier
                    .width(100.dp)
                    .height(36.dp)
                    .constrainAs(addButton)
                    {
                        end.linkTo(parent.end, 8.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                    },
                contentPadding = PaddingValues(
                    horizontal = 8.dp,
                    vertical = 8.dp
                ),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkBlue
                )
            ) {
                Text(
                    text = "Add to Cart",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}