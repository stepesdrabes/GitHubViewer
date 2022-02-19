package cz.stepes.githubviewer.util

enum class ResourceErrorState {
    NotFound,
    NetworkError
}

sealed class Resource<T>(val data: T? = null, val message: ResourceErrorState? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(state: ResourceErrorState, data: T? = null) : Resource<T>(data, state)
    class Loading<T> : Resource<T>()
}