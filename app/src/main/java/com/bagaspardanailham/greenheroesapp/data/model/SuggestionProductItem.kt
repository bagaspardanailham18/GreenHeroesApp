package com.bagaspardanailham.greenheroesapp.data.model

import androidx.annotation.DrawableRes
import com.bagaspardanailham.greenheroesapp.R

data class SuggestionProductItem(
    val name: String,
    val price: String,
    @DrawableRes val image: Int
)

val suggestionProductItemList = listOf(
    SuggestionProductItem(
        name = "Recycled Totebag",
        price = "Rp200.000",
        image = R.drawable.totebag
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
