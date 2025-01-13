package com.bagaspardanailham.greenheroesapp.ui.components

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.palette.graphics.Palette
import com.bagaspardanailham.greenheroesapp.R
import com.bagaspardanailham.greenheroesapp.data.model.SuggestionProductItem
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_medium
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_regular

@Composable
fun ShopProductItemCard(product: SuggestionProductItem) {
    val context = LocalContext.current
    val bitmap = remember {
        BitmapFactory.decodeResource(context.resources, product.image)
    }
    val palette = remember {
        Palette.from(bitmap).generate()
    }
    val darkVibrantSwatch = palette.lightMutedSwatch

    Box(
        modifier = Modifier
            .padding(6.dp)
            .width(180.dp)
            .height(246.dp)
            .background(
                color = darkVibrantSwatch?.let { Color(it.rgb) } ?: Color.LightGray,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(start = 14.dp, top = 24.dp, end = 14.dp, bottom = 14.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = product.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                fontFamily = poppins_medium
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                text = product.price,
                maxLines = 1,
                fontSize = 12.sp,
                fontFamily = poppins_regular,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(id = R.color.dark_green)
            )
        }
        Image(
            modifier = Modifier
                .size(144.dp)
                .offset(y = -30.dp),
            painter = painterResource(id = product.image),
            contentDescription = "Aglaonema"
        )
    }
}

fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

@Preview
@Composable
fun ShopItemPreview() {
    val list = listOf(
        SuggestionProductItem(
            "Aglaonema",
            "Rp.10000",
            R.drawable.aglaonema
        ),
        SuggestionProductItem(
            "Recycled Totebag",
            "Rp.10000",
            R.drawable.totebag
        ),
        SuggestionProductItem(
            "Maple Seed",
            "Rp.10000",
            R.drawable.maple_seed
        ),
        SuggestionProductItem(
            "Aglaonema",
            "Rp.10000",
            R.drawable.aglaonema
        )
    )
    ShopProductItemCard(
        SuggestionProductItem(
            "Aglaonema",
            "Rp.10000",
            R.drawable.aglaonema
        )
    )
}

