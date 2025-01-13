package com.bagaspardanailham.greenheroesapp.data.model

data class ChipCategory(
    val name: String,
    val isSelected: Boolean
)

val suggestionCategoryItems = listOf(
    ChipCategory("Favorite", false),
    ChipCategory("Tree", false),
    ChipCategory("Eco Products", false)
)

val shopCategoryItems = listOf(
    ChipCategory("All", false),
    ChipCategory("Plants", false),
    ChipCategory("Gardening Kits", false),
    ChipCategory("Recycled Product", false)
)
