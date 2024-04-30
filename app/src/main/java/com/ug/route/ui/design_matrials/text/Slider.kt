package com.ug.route.ui.design_matrials.text

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.*
import com.ug.route.R
import com.ug.route.ui.theme.DarkBlue
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun SliderBanner(
    modifier: Modifier = Modifier,
    onClickOffer: (Int) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)
    val images = listOf(
        painterResource(id = R.drawable.image_one),
        painterResource(id = R.drawable.image_two),
        painterResource(id = R.drawable.image_three)
    )

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column(
        modifier = modifier
    ) {
        HorizontalPager(
            count = images.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) { page ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .clickable {
                        onClickOffer(page)
                    }
                    .graphicsLayer {

                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {

                ConstraintLayout(
                    modifier.fillMaxSize()
                ) {

                    val (image,indicator) = createRefs()

                    Image(
                        painter = images[page],
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .constrainAs(image){

                            }
                    )

                    HorizontalPagerIndicator(
                        pagerState = pagerState,
                        modifier = Modifier.constrainAs(indicator){
                            bottom.linkTo(image.bottom,16.dp)
                            start.linkTo(image.start)
                            end.linkTo(image.end)
                        },
                        activeColor = DarkBlue,
                        inactiveColor = Color.White
                    )

                }
            }
        }
    }
}

@ExperimentalPagerApi
@Preview(showSystemUi = true)
@Composable
fun SliderBannerPreview() {
    SliderBanner(){

    }
}