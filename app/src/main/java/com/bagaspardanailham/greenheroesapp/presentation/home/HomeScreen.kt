package com.bagaspardanailham.greenheroesapp.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.bagaspardanailham.greenheroesapp.R
import com.bagaspardanailham.greenheroesapp.data.model.SuggestionProductItem
import com.bagaspardanailham.greenheroesapp.data.model.suggestionCategoryItems
import com.bagaspardanailham.greenheroesapp.data.model.suggestionProductItemList
import com.bagaspardanailham.greenheroesapp.ui.components.GHCategoryChips
import com.bagaspardanailham.greenheroesapp.ui.components.GHCategoryTabRV
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_bold
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_light
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_medium
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_regular

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .height(2000.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        // Location
//        Row(
//            modifier = Modifier.padding(top = 20.dp)
//        ) {
//            Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "location")
//            Spacer(modifier = Modifier.width(6.dp))
//            Text(text = "Sumedang", fontFamily = poppins_light, fontSize = 11.sp)
//        }
        // Hero Section
        Row(
            modifier = Modifier
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val annotatedString = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black, fontFamily = poppins_regular)) {
                    append("Let’s Become a Green ")
                }
                withStyle(style = SpanStyle(color = Color.Black, fontFamily = poppins_bold)) {
                    append("Superheroes")
                }
            }
            Text(
                modifier = Modifier.weight(4f),
                text = annotatedString,
                fontSize = 34.sp,
                lineHeight = 1.2.em
            )
            Image(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .size(48.dp)
                    .weight(1f),
                painter = painterResource(
                    id = R.drawable.person
                ),
                contentDescription = "avatar"
            )
        }

        // Wallet Section
        WalletSection()

        // SuggestionSection
        SuggestionSection()
    }
}

@Composable
fun WalletSection() {
    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 25.dp)
            .background(Color.White)
            .height(75.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 400.dp)
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = colorResource(id = R.color.light_green).copy(alpha = 0.12f),
                            shape = CircleShape
                        )
                        .padding(8.dp),
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(32.dp),
                        imageVector = Icons.Filled.Wallet,
                        tint = colorResource(id = R.color.light_green),
                        contentDescription = "wallet"
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Rp30.000",
                        fontFamily = poppins_medium,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Wallet",
                        fontFamily = poppins_light,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(Color.LightGray)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "10.000",
                        fontFamily = poppins_medium,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Point",
                        fontFamily = poppins_light,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun SuggestionSection() {
    Column(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.darker_green),
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 18.dp, top = 18.dp, end = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Our Latest Products",
                fontSize = 20.sp,
                fontFamily = poppins_medium,
                color = Color.White
            )
            Icon(
                imageVector = Icons.Filled.ArrowRightAlt,
                tint = Color.White,
                contentDescription = "seeAll"
            )
        }
        GHCategoryTabRV(
            labelColor = colorResource(id = R.color.chip_grey),
            leadingIconColor = colorResource(id = R.color.chip_grey),
            trailingIconColor = colorResource(id = R.color.chip_grey),
            selectedContainerColor = colorResource(id = R.color.light_green),
            selectedLabelColor = Color.Black,
            selectedLeadingIconColor = Color.Black,
            selectedTrailingIconColor = Color.Black,
            selectedBorderColor = null,
            unSelectedBorderColor = null,
            dataList = suggestionCategoryItems
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            text = "Based on current climate of your region",
            fontSize = 12.sp,
            fontFamily = poppins_light,
            color = Color.White
        )
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 14.dp, bottom = 18.dp)
        ) {
            items(suggestionProductItemList) { product ->
                SuggestionProductItemCard(product)
            }
        }
    }
}

@Composable
fun SuggestionProductItemCard(product: SuggestionProductItem) {
    Column(
        modifier = Modifier
            .padding(end = 10.dp)
            .width(120.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        colorResource(id = R.color.cream)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(120.dp),
            painter = painterResource(id = product.image),
            contentDescription = product.name
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            text = product.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            fontFamily = poppins_medium
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 8.dp),
            text = product.price,
            maxLines = 1,
            fontSize = 12.sp,
            fontFamily = poppins_regular,
            overflow = TextOverflow.Ellipsis,
            color = colorResource(id = R.color.dark_green)
        )
    }
}

@Composable
@Preview(device = Devices.PIXEL, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun InputScreenPreview() {
    HomeScreen()
}

//@Composable
//@Preview(device = Devices.PIXEL, showBackground = true)
//fun SuggestionProductItemPreview() {
//    SuggestionProductItem()
//}