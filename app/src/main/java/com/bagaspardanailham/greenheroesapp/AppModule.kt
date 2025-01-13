package com.bagaspardanailham.greenheroesapp

import com.bagaspardanailham.greenheroesapp.presentation.vm.ShopVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

private val loadContentInActivityModules by lazy {
    loadKoinModules(
        listOf(
            viewModelModule
        )
    )
}

fun injectContentInActivityKoin() = loadContentInActivityModules



private val viewModelModule: Module = module {
    scope<MainActivity> {
        viewModel { ShopVM() }
    }
}

val testModule = listOf(
    viewModelModule
)