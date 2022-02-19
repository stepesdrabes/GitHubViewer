package cz.stepes.githubviewer.di

import cz.stepes.githubviewer.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelsModule = module {
    viewModel { UserViewModel(get()) }
}