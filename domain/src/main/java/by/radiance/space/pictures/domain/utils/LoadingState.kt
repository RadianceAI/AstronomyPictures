package by.radiance.space.pictures.domain.utils

import by.radiance.space.pictures.domain.entity.Picture

sealed class LoadingState<T> {
    data class Success<T>(val picture: T): LoadingState<T>()
    data class Error<T>(val throwable: Throwable): LoadingState<T>()
}

fun Picture.asState() = LoadingState.Success(this)