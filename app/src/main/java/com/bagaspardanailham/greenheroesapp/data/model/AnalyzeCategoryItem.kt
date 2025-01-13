package com.bagaspardanailham.greenheroesapp.data.model

import java.util.UUID

data class AnalyzeCategoryItem(
    val id: UUID,
    val name: String,
    val isSelected: Boolean
)

val analyzeCategoryItems = listOf(
    AnalyzeCategoryItem(UUID.randomUUID(), "Plant Identification", false),
    AnalyzeCategoryItem(UUID.randomUUID(), "Plant Desease Detection", false),
    AnalyzeCategoryItem(UUID.randomUUID(), "Water & Soil Quality Assesment", false),
    AnalyzeCategoryItem(UUID.randomUUID(), "Waste Sorting", false)
)