package com.bagaspardanailham.greenheroesapp.presentation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object HomeScreen: Screen()
    @Serializable
    data object ShopScreen: Screen()
    @Serializable
    data object AnalyzeScreen: Screen()
    @Serializable
    data object CommunityScreen: Screen()
    @Serializable
    data object VideoScreen: Screen()
}