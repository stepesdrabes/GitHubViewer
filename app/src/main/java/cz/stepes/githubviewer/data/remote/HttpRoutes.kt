package cz.stepes.githubviewer.data.remote

object HttpRoutes {
    private const val BASE_URL = "https://api.github.com"

    const val USER_URL = "$BASE_URL/users"
    const val REPOS_URL = "$BASE_URL/repos"

    const val REPOS = "repos"
    const val COMMITS = "commits?page=0&per_page=10"
    const val LANGUAGES = "languages"
    const val BRANCHES = "branches"

    const val GITHUB_BASE_URL = "https://github.com"
}