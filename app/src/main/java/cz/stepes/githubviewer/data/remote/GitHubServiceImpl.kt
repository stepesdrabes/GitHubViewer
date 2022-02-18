package cz.stepes.githubviewer.data.remote

import io.ktor.client.*

class GitHubServiceImpl(
    private val client: HttpClient
) : GitHubService {

}