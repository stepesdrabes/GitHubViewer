package cz.stepes.githubviewer

import android.app.Application
import cz.stepes.githubviewer.di.GitHubModule
import cz.stepes.githubviewer.di.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class GitViewerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@GitViewerApp)
            modules(GitHubModule, ViewModelsModule)
        }
    }
}