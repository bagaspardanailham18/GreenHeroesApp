package com.bagaspardanailham.greenheroesapp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.bagaspardanailham.greenheroesapp.R
import com.bagaspardanailham.greenheroesapp.data.model.SuggestionProductItem
import com.bagaspardanailham.greenheroesapp.data.model.shopCategoryItems
import com.bagaspardanailham.greenheroesapp.data.model.suggestionCategoryItems
import com.bagaspardanailham.greenheroesapp.presentation.vm.ShopVM
import com.bagaspardanailham.greenheroesapp.ui.components.GHCategoryTabRV
import com.bagaspardanailham.greenheroesapp.ui.components.ShopProductItemCard
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_medium
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun ShopScreen(viewModel: ShopVM) {
    val bannerList = listOf(
        R.drawable.gh_banner_1,
        R.drawable.gh_banner_2,
        R.drawable.gh_banner_3
    )

    val productList = listOf(
        SuggestionProductItem(
            name = "Recycled Totebag",
            price = "Rp200.000",
            image = R.drawable.totebag
        ),
        SuggestionProductItem(
            name = "Shovel",
            price = "Rp250.000",
            image = R.drawable.shovel
        ),
        SuggestionProductItem(
            name = "Maple Seed",
            price = "Rp100.000",
            image = R.drawable.maple_seed
        ),
        SuggestionProductItem(
            name = "Clippers",
            price = "Rp200.000",
            image = R.drawable.clippers
        ),
        SuggestionProductItem(
            name = "Aglaonema",
            price = "Rp250.000",
            image = R.drawable.aglaonema
        ),
        SuggestionProductItem(
            name = "Maple Seed",
            price = "Rp100.000",
            image = R.drawable.maple_seed
        )
    )

    var pagerState = rememberPagerState(initialPage = 0)
//    Scaffold(
//        topBar = {
//            SearchBar(
//                query = "",//text showed on SearchBar
//                onQueryChange = {}, //update the value of searchText
//                onSearch = {}, //the callback to be invoked when the input service triggers the ImeAction.Search action
//                active = true, //whether the user is searching or not
//                onActiveChange = { }, //the callback to be invoked when this search bar's active state is changed
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ) {
//            }
//        }
//    ) { _ ->
//
//    }

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
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.safeDrawing
            )
    ) {
        SearchBar(
            colors = SearchBarDefaults.colors(
                containerColor = colorResource(id = R.color.light_grey),
                dividerColor = colorResource(id = R.color.light_grey)
            ),
            query = viewModel.searchQuery,//text showed on SearchBar,
            onQueryChange = {
                viewModel.onSearchQueryChange(it)
            }, //update the value of searchText
            onSearch = {}, //the callback to be invoked when the input service triggers the ImeAction.Search action
            active = false, //whether the user is searching or not
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    tint = colorResource(id = R.color.dark_grey),
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CameraAlt,
                    tint = colorResource(id = R.color.dark_grey),
                    contentDescription = "Camera"
                )
            },
            placeholder = {
                Text(
                    text = "Find tree, plant, or flower",
                    fontFamily = poppins_medium,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.dark_grey)
                )
            },
            onActiveChange = { }, //the callback to be invoked when this search bar's active state is changed
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

        }

        Box {
            HorizontalPager(
                count = bannerList.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier
                    .height(114.dp)
                    .fillMaxWidth()
            ) { page ->
                Card(
                    shape = RoundedCornerShape(12.dp),
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
                    Image(
                        painter = painterResource(id = bannerList[page]),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.White,
                inactiveColor = colorResource(id = R.color.chip_grey),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }

        GHCategoryTabRV(
            dataList = shopCategoryItems,
            labelColor = colorResource(id = R.color.chip_grey),
            leadingIconColor = colorResource(id = R.color.chip_grey),
            trailingIconColor = colorResource(id = R.color.chip_grey),
            selectedContainerColor = Color.Transparent,
            selectedLabelColor = Color.Black,
            selectedLeadingIconColor = Color.Black,
            selectedTrailingIconColor = Color.Black,
            selectedBorderColor = Color.Black,
            unSelectedBorderColor = colorResource(id = R.color.chip_light_grey),
            selectedBorderWidth = 2.dp,
            unselectedBorderWidth = 1.dp
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 100.dp)
        ) {
            productList.let { products ->
                items(products.size) {
                    ShopProductItemCard(products[it])
                }
            }
        }
    }
}