package by.radiance.space.pictures.domain.utils

sealed class LoadingState<T> {
    data class Success<T>(val data: T): LoadingState<T>()
    data class Error<T>(val throwable: Throwable): LoadingState<T>()
}

fun <T> T.asState() = LoadingState.Success(this)