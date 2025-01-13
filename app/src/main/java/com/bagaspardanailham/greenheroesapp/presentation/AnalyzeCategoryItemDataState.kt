package com.bagaspardanailham.greenheroesapp.presentation

import androidx.compose.runtime.toMutableStateList
import com.bagaspardanailham.greenheroesapp.data.model.AnalyzeCategoryItem

class AnalyzeCategoryItemDataState {
    var analyzeCategoryList = mutableListOf<AnalyzeCategoryItem>()

    fun onItemSelected(selectedItemData: AnalyzeCategoryItem) {
        val iterator = analyzeCategoryList.listIterator()
        while (iterator.hasNext()) {
            val listItem = iterator.next()
            iterator.set(
                if (listItem.id == selectedItemData.id) {
                    selectedItemData
                } else {
                    listItem.copy(isSelected = false)
                }
            )
        }
    }

    fun setAnalyzeCatList(list: List<AnalyzeCategoryItem>) {
        this.analyzeCategoryList = list.toMutableStateList()
    }
}