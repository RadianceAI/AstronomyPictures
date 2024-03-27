package by.radiance.space.pictures.presenter.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

fun Shapes(cornerSize: Int): Shapes {
    return Shapes(
        small = RoundedCornerShape(cornerSize.dp),
        medium = RoundedCornerShape(cornerSize.dp),
        large = RoundedCornerShape(cornerSize.dp)
    )
}