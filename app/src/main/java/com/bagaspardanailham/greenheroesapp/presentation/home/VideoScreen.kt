package com.bagaspardanailham.greenheroesapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.bagaspardanailham.greenheroesapp.R
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_medium
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_regular
import com.bagaspardanailham.greenheroesapp.ui.utils.CoilImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VideoScreen() {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("All", "Documenter", "Conference", "Short")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.video_bg_green)),
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
                    text = "Videos",
                    fontSize = 32.sp,
                    color = colorResource(id = R.color.light_green),
                    fontFamily = poppins_medium
                )
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = Color.White,
                    contentDescription = "Search"
                )
            }
        }
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                androidx.compose.material.ScrollableTabRow(
                    selectedTabIndex = rememberPagerState().currentPage,
                    backgroundColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    edgePadding = 0.dp,
                    divider = {
                        HorizontalDivider(
                            color = Color.Transparent
                        )
                    },
                    indicator = { tabPos ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.pagerTabIndicatorOffset(
                                rememberPagerState(),
                                tabPos
                            ),
                            height = 2.dp,
                            color = colorResource(id = R.color.light_green)
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = {
                                Text(
                                    text = title,
                                    color = if (tabIndex == index) {
                                        colorResource(id = R.color.light_green)
                                    } else colorResource(id = R.color.chip_grey),
                                    fontFamily = poppins_medium,
                                    fontSize = 16.sp
                                )
                            },
                            selected = rememberPagerState().currentPage == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }
                when (tabs[tabIndex]) {
                    "All" -> AllVideosFragment()
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AllVideosFragment() {
    val highlightList = listOf(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRhvgd_RU80uqlNZQENckAXKtknVIdQPCePGQ&s",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQamBmIBexZy4Ml38mw54y_U2N29w3TMA6hnw&s",
        "https://zerowaste.id/wp-content/uploads/2022/12/film-trashed.jpeg"
    )

    val shortList = listOf(
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgTkAi3ks0F_Z5YHb7Z5zutdVlf6NR4YXFyQ&s",
        "https://www.howespercival.com/images/custom/a3/e0a617b27b84b3a207cef4e0d12275/w1090/h1058/wpojpg/images/uploads/a3e0a617b27b84b3a207cef4e0d12275.webp",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlz55_GKSC2U9op6GYTEDZF8kvaJrJ-tFAA227XvqfR88AooVuHq7zgehZzIRxt-h03uM&usqp=CAU"
    )
    var pagerState = rememberPagerState(initialPage = 1)

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = highlightList.size,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 30.dp, vertical = 15.dp)
        ) { page ->
            Card(
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
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
                Box(modifier = Modifier.fillMaxSize()) {
                    CoilImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        imgUrl = highlightList[page],
                        contentScale = ContentScale.Crop,
                        colorFilter = null
                    )
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .align(Alignment.Center)
                            .alpha(.5f)
                            .clip(CircleShape)
                            .background(color = Color.Black)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.Center),
                            imageVector = Icons.Filled.PlayArrow,
                            tint = Color.White,
                            contentDescription = "Play Icon"
                        )
                    }
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color.White,
            inactiveColor = colorResource(id = R.color.chip_grey)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp),
                text = "Documenter",
                fontFamily = poppins_medium,
                fontSize = 20.sp,
                color = Color.White
            )
            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 30.dp, vertical = 15.dp)
            ) {
                items(highlightList.size) {
                    LanscapeVideoCardItem(highlightList[it])
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 30.dp, top = 20.dp),
                text = "Shorts",
                fontFamily = poppins_medium,
                fontSize = 20.sp,
                color = Color.White
            )
            LazyRow(
                modifier = Modifier,
                contentPadding = PaddingValues(horizontal = 30.dp, vertical = 15.dp)
            ) {
                items(shortList.size) {
                    PortraitVideoCardItem(shortList[it])
                }
            }
        }
    }
}

@Composable
fun LanscapeVideoCardItem(thumbnailUrl: String) {
    Column(
        modifier = Modifier
            .padding(end = 10.dp)
    ) {
        Card(
            modifier = Modifier
                .height(110.dp)
                .width(180.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            CoilImage(
                modifier = Modifier.fillMaxSize(),
                imgUrl = thumbnailUrl,
                contentScale = ContentScale.Crop,
                colorFilter = null
            )
        }

        Text(
            modifier = Modifier.padding(top = 9.dp),
            fontFamily = poppins_regular,
            text = "Eps1. Jantung Kehidupan",
            fontSize = 12.sp,
            color = Color.White
        )
    }
}

@Composable
fun PortraitVideoCardItem(thumbnailUrl: String) {
    Card(
        modifier = Modifier
            .padding(end = 10.dp)
            .height(190.dp)
            .width(135.dp)
            .clip(RoundedCornerShape(14.dp))
    ) {
        CoilImage(
            modifier = Modifier.fillMaxSize(),
            imgUrl = thumbnailUrl,
            contentScale = ContentScale.Crop,
            colorFilter = null
        )
    }
}