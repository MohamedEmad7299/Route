package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ug.route.ui.theme.ClearSky
import com.ug.route.ui.theme.LightBlue


@Preview(showSystemUi = true)
@Composable
fun CategoriesSelectionPreview(){

    CategoriesSelection(
        firstIndex = "Music"
    ){}
}

@Composable
fun CategoriesSelection(
    modifier: Modifier = Modifier,
    firstIndex : String,
    onCategoryChange : (String) -> Unit
){

    var selectedCategoryIndex by remember { mutableIntStateOf(firstIndex.hashCode()) }

    val categoriesNames = listOf(
        "Music", "Men's Fashion", "Women's Fashion", "Super Market", "Baby & Toys",
        "Home", "Books", "Beauty & Health", "Mobiles", "Electronics"
    )

    Box(
        modifier = modifier
            .padding(start = 16.dp)
            .border(width = 1.dp, color = ClearSky, RoundedCornerShape(topStart = 16.dp))
            .background(LightBlue, RoundedCornerShape(topStart = 16.dp))
            .fillMaxHeight()
            .width(140.dp)
    ){
        Column(
            Modifier.fillMaxSize()
        ){
            categoriesNames.forEach{ categoryName ->
                CategoryCard(
                    categoryName = categoryName,
                    isSelected = selectedCategoryIndex == categoryName.hashCode(),
                    onCategoryClicked = { clickedIndex ->
                        if (selectedCategoryIndex != clickedIndex)
                            selectedCategoryIndex = clickedIndex
                        onCategoryChange(categoryName)
                    },
                    shape = if (categoryName == categoriesNames[0]) RoundedCornerShape(topStart = 16.dp) else RectangleShape
                )
            }
        }
    }
}