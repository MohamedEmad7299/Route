package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ug.route.R
import com.ug.route.ui.theme.ClearSky
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductCard(
    imageResource: Int = R.drawable.laptops,
    productName: String = "UG7299 RTX4090",
    productReview: String = "4.9",
    productPrice: Int = 8900,
    isFavourite: Boolean = false,
    onClickFavButton: () -> Unit,
    onClickAddButton: () -> Unit
){

    Card(
        modifier = Modifier
            .height(240.dp)
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, ClearSky)
    ) {


        ConstraintLayout(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        ) {

            val (image,
                name,
                review,
                price,
                favButton,
                addButton) = createRefs()


            Image(
                painter = painterResource(id = imageResource),
                modifier = Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                    }
                    .height(120.dp)
                    .fillMaxWidth(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            Text18(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .constrainAs(name){
                    start.linkTo(parent.start)
                    top.linkTo(image.bottom,8.dp)
                },
                fontSize = 14,
                text = productName,
                color = Color(0xFF06004F),
                textAlign = TextAlign.Start
            )


            Row(
                modifier = Modifier.constrainAs(price){
                    start.linkTo(parent.start,8.dp)
                    top.linkTo(name.bottom,8.dp)
                },
                verticalAlignment = Alignment.CenterVertically
            ){
                Text18(
                    fontSize = 14,
                    modifier = Modifier.padding(end = 16.dp),
                    text = "EGP ${NumberFormat.getNumberInstance(Locale.US).format(productPrice)}",
                    color = Color(0xFF06004F)
                )

                Text(
                    text = "${NumberFormat.getNumberInstance(Locale.US).format(productPrice+300)} EGP",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        fontWeight = FontWeight(400),
                        color = ClearSky,
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.LineThrough
                    )
                )
            }

            Row(
                modifier = Modifier.constrainAs(review){
                    start.linkTo(parent.start,8.dp)
                    top.linkTo(price.bottom,8.dp)
                },
                verticalAlignment = Alignment.CenterVertically
            ){
                Text18(
                    Modifier.padding(end = 8.dp),
                    fontSize = 14,
                    text = "Review (${productReview})",
                    color = Color(0xFF06004F)
                )

                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = "")
            }


            IconButton(
                modifier = Modifier.constrainAs(favButton) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
                onClick = onClickFavButton
            ){
                Image(
                    modifier = Modifier
                        .size(40.dp),
                    painter = if (isFavourite) painterResource(id = R.drawable.fav_focus) else painterResource(id = R.drawable.fav_unfocus),
                    contentDescription = "")
            }


            IconButton(
                modifier = Modifier.constrainAs(addButton) {
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
                onClick = onClickAddButton
            ){
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(id = R.drawable.plus_icon),
                    contentDescription = "")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductCardPreview(){


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(Color.White)
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(2) {
            ProductCard(onClickFavButton = { /*TODO*/ }) {
                
            }
        }
    }

}