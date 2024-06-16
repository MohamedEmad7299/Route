package com.ug.route.ui.design_matrials.text

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue
import com.ug.route.ui.theme.DarkPurple
import com.ug.route.ui.theme.LightBlue


@SuppressLint("SuspiciousIndentation")
@Composable
fun CategoryCard(
    categoryName : String,
    isSelected : Boolean,
    onCategoryClicked: (Int) -> Unit,
    shape: Shape = RectangleShape
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(59.dp)
            .background(if (isSelected) Color.White else LightBlue , shape = shape)
            .clickable { onCategoryClicked(categoryName.hashCode()) }
    ){

        if (isSelected)

        Spacer(modifier = Modifier
            .padding(start = 8.dp , top = 8.dp , bottom = 8.dp)
            .background(DarkBlue , RoundedCornerShape(8.dp))
            .width(8.dp)
            .height(43.dp)
        )

        Text(
            text = categoryName,
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 8.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontWeight = FontWeight(600),
                color = DarkPurple,
                textAlign = TextAlign.Center)
        )
    }
}