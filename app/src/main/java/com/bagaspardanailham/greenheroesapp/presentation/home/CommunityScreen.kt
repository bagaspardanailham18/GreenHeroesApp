package com.bagaspardanailham.greenheroesapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.bagaspardanailham.greenheroesapp.R
import com.bagaspardanailham.greenheroesapp.ui.components.GHProgressBar
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_light
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_medium
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_regular
import com.bagaspardanailham.greenheroesapp.ui.utils.CoilImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@Composable
fun CommunityScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        item {
            // Header
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Community",
                    fontSize = 32.sp,
                    fontFamily = poppins_medium
                )
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = Color.Black,
                    contentDescription = "Search"
                )
            }
        }

        item {
            // Reads For You Carousel
            Column(
                modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Reads For You",
                        fontSize = 16.sp,
                        fontFamily = poppins_medium
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowRightAlt,
                        tint = Color.Black,
                        contentDescription = "Search"
                    )
                }

                ReadsForYouCarouselItemCard()
            }
        }

        item {
            // Challange & Donation
            Column(
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Challange & Donation",
                        fontSize = 16.sp,
                        fontFamily = poppins_medium
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowRightAlt,
                        tint = Color.Black,
                        contentDescription = "Challange & Donation"
                    )
                }

                ChallangeAndDonationSection()
            }
        }

        item {
            // Event & Competition
            Column(
                modifier = Modifier.padding(top = 30.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Event & Competition",
                        fontSize = 16.sp,
                        fontFamily = poppins_medium
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowRightAlt,
                        tint = Color.Black,
                        contentDescription = "Event & Competition"
                    )
                }

                EventAndCompetitionSection()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview
fun ReadsForYouCarouselItemCard() {
    val carouselReadList = listOf(
        "https://www.environmentshow.com/wp-content/uploads/2018/12/Best-environmental-events-world-international.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpuQh1iWA9u4q2uM7fXOmJEIkyo4r1IACgQd45_skj825N7LKit7B0RkbFJPjXux3nbZI&usqp=CAU",
        "https://one-more-tree.org/wp-content/uploads/2023/08/srodowisko-i-radosna-koncepcja-wolontariuszy-768x512.jpg"
    )
    var pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
    ) {
        HorizontalPager(
            count = carouselReadList.size,
            state = pagerState,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
        ) { page ->
            Box(modifier = Modifier
                .background(Color.Black)
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
                CoilImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp),
                    contentScale = ContentScale.Crop,
                    colorFilter = null,
                    imgUrl = carouselReadList[page]
                )
            }
        }

        Row(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 1f
                    )
                )
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Column(
            ) {
                Text(
                    modifier = Modifier
                        .width(200.dp),
                    maxLines = 2,
                    fontSize = 13.sp,
                    fontFamily = poppins_medium,
                    color = Color.White,
                    text = "Hijaukan Kembali Bumi dengan Menanam Pohon"
                )
                Text(
                    fontSize = 10.sp,
                    fontFamily = poppins_regular,
                    color = Color.White,
                    text = "Jagabersama.com"
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.White,
                inactiveColor = colorResource(id = R.color.chip_grey),
            )
        }
    }
}

@Composable
fun ChallangeAndDonationSection() {
    val challangeList = listOf(
        "https://www.environmentshow.com/wp-content/uploads/2018/12/Best-environmental-events-world-international.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpuQh1iWA9u4q2uM7fXOmJEIkyo4r1IACgQd45_skj825N7LKit7B0RkbFJPjXux3nbZI&usqp=CAU",
        "https://one-more-tree.org/wp-content/uploads/2023/08/srodowisko-i-radosna-koncepcja-wolontariuszy-768x512.jpg"
    )

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(start = 16.dp)
    ) {
        items(challangeList) { challange ->
            ChallangeAndDonationCardComponent(challange)
        }
    }
}

@Composable
fun ChallangeAndDonationCardComponent(imgUrl: String) {
    ElevatedCard(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp, end = 12.dp)
            .width(218.dp)
            .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 400.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(12.dp)
        ) {
            CoilImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(98.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop,
                colorFilter = null,
                imgUrl = imgUrl
            )
            Text(
                modifier = Modifier.padding(top = 11.dp),
                text = "30 Day Eco-Habit Challenge",
                fontFamily = poppins_medium,
                fontSize = 13.sp,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Jagabersama.com",
                fontFamily = poppins_regular,
                fontSize = 10.sp
            )
            GHProgressBar(
                modifier = Modifier.padding(top = 10.dp),
                progress = 0.5f
            )
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "399 Participants",
                    fontFamily = poppins_light,
                    fontSize = 10.sp
                )
                Text(
                    text = "20 Days Left",
                    fontFamily = poppins_light,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun EventAndCompetitionSection() {
    val eventList = listOf(
        "https://www.environmentshow.com/wp-content/uploads/2018/12/Best-environmental-events-world-international.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpuQh1iWA9u4q2uM7fXOmJEIkyo4r1IACgQd45_skj825N7LKit7B0RkbFJPjXux3nbZI&usqp=CAU",
        "https://one-more-tree.org/wp-content/uploads/2023/08/srodowisko-i-radosna-koncepcja-wolontariuszy-768x512.jpg"
    )

    Column(
        modifier = Modifier.padding(top = 12.dp)
    ) {
        eventList.forEach { event ->
            EventAndCompetitionCardComponent(event)
        }
    }
}

@Composable
fun EventAndCompetitionCardComponent(imgUrl: String) {
    ElevatedCard(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 400.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            CoilImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                colorFilter = null,
                imgUrl = imgUrl
            )
            Column(
                modifier = Modifier.padding(start = 20.dp).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Fun Run 5K, 10K, Half Marathon, Full Marathon (Bali 2025)",
                    fontFamily = poppins_medium,
                    fontSize = 12.sp,
                    minLines = 3,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Kahf X GreenHeroes",
                    fontFamily = poppins_regular,
                    fontSize = 10.sp
                )
            }
        }
    }
}