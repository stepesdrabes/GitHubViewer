package cz.stepes.githubviewer.di

import cz.stepes.githubviewer.ui.search.SearchViewModel
import cz.stepes.githubviewer.ui.test.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelsModule = module {
    viewModel { TestViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}