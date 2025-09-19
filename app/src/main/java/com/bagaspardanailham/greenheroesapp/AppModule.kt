package com.bagaspardanailham.greenheroesapp

import com.bagaspardanailham.greenheroesapp.presentation.vm.AnalyzeVM
import com.bagaspardanailham.greenheroesapp.presentation.vm.ShopVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun provideKoinModule() = loadKoinModules(
        listOf(
            viewModelModule
        )
    )

private val viewModelModule = module {
    viewModelOf(::ShopVM)
    viewModelOf(::AnalyzeVM)
}