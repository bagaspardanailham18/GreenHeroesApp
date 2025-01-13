package com.bagaspardanailham.greenheroesapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagaspardanailham.greenheroesapp.R
import com.bagaspardanailham.greenheroesapp.data.model.ChipCategory
import com.bagaspardanailham.greenheroesapp.ui.theme.poppins_medium

@Composable
fun GHCategoryTabRV(
    dataList: List<ChipCategory>,
    labelColor: Color?,
    leadingIconColor: Color?,
    trailingIconColor: Color?,
    selectedContainerColor: Color?,
    selectedLabelColor: Color?,
    selectedLeadingIconColor: Color?,
    selectedTrailingIconColor: Color?,
    selectedBorderColor: Color?,
    unSelectedBorderColor: Color?,
    selectedBorderWidth: Dp? = null,
    unselectedBorderWidth: Dp? = null
) {
    var catSelected by remember {
        mutableStateOf(dataList.first().name)
    }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 14.dp, bottom = 10.dp)
    ) {
        items(dataList) { category ->
            GHCategoryChips(
                catSelected,
                catSelected = {
                    catSelected = it
                },
                category,
                labelColor,
                leadingIconColor,
                trailingIconColor,
                selectedContainerColor,
                selectedLabelColor,
                selectedLeadingIconColor,
                selectedTrailingIconColor,
                selectedBorderColor,
                unSelectedBorderColor,
                selectedBorderWidth,
                unselectedBorderWidth
            )
        }
    }
}

@Composable
fun GHCategoryChips(
    selectedCategory: String,
    catSelected: (String) -> Unit,
    category: ChipCategory,
    labelColor: Color?,
    leadingIconColor: Color?,
    trailingIconColor: Color?,
    selectedContainerColor: Color?,
    selectedLabelColor: Color?,
    selectedLeadingIconColor: Color?,
    selectedTrailingIconColor: Color?,
    selectedBorderColor: Color?,
    unSelectedBorderColor: Color?,
    selectedBorderWidth: Dp? = null,
    unselectedBorderWidth: Dp? = null
) {
    FilterChip(
        selected = selectedCategory == category.name,
        onClick = {
            catSelected(category.name)
        },
        colors = SelectableChipColors(
            containerColor = Color.Transparent,
            labelColor = labelColor ?: colorResource(id = R.color.chip_grey),
            leadingIconColor = leadingIconColor ?: colorResource(id = R.color.chip_grey),
            trailingIconColor = trailingIconColor ?: colorResource(id = R.color.chip_grey),
            disabledContainerColor = Color.Transparent,
            disabledLabelColor = Color.Black,
            disabledLeadingIconColor = Color.Black,
            disabledTrailingIconColor = Color.Black,
            selectedContainerColor = selectedContainerColor
                ?: colorResource(id = R.color.light_green),
            selectedLabelColor = selectedLabelColor ?: Color.Black,
            selectedLeadingIconColor = selectedLeadingIconColor ?: Color.Black,
            selectedTrailingIconColor = selectedTrailingIconColor ?: Color.Black,
            disabledSelectedContainerColor = Color.Transparent
        ),
        border = if (selectedCategory == category.name) {
            BorderStroke(selectedBorderWidth ?: 0.dp, selectedBorderColor ?: Color.Transparent)
        } else BorderStroke(unselectedBorderWidth ?: 1.dp, unSelectedBorderColor ?: Color.White),
        shape = CircleShape,
        label = {
            Text(
                text = category.name,
                fontFamily = poppins_medium,
                fontSize = 16.sp
            )
        },
        modifier = Modifier
            .padding(end = 10.dp)
    )
}