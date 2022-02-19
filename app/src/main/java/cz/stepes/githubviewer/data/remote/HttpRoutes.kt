package cz.stepes.githubviewer.data.remote

object HttpRoutes {
    private const val BASE_URL = "https://api.github.com"

    const val USER_URL = "$BASE_URL/users"
    const val REPOS = "repos"
}