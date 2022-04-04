package by.radiance.space.picrures.presenter.utils

import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.presenter.state.PictureUiState

fun Picture.toUiState() = PictureUiState.Success(this)