package cz.stepes.githubviewer.di

import cz.stepes.githubviewer.data.remote.GitHubService
import org.koin.dsl.module

val GitHubModule = module {
    single { GitHubService.create() }
}