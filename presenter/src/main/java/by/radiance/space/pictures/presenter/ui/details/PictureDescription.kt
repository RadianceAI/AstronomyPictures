package by.radiance.space.pictures.presenter.ui.details

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import by.radiance.space.pictures.domain.entity.Id
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.domain.entity.toId
import by.radiance.space.pictures.domain.presenter.state.PictureUiState
import by.radiance.space.pictures.domain.utils.DateUtil
import by.radiance.space.pictures.presenter.R
import by.radiance.space.pictures.presenter.ui.theme.AstronomyPicturesTheme
import java.util.Date

@Composable
fun PictureDescription(
    modifier: Modifier = Modifier,
    pictureUiState: PictureUiState,
) {
    if (pictureUiState !is PictureUiState.Success) return

    val context = LocalContext.current
    val picture = pictureUiState.picture

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .onLongClick {
                    context.copyToClipboard(picture.title)
                },
            text = picture.title ?: "",
            style = MaterialTheme.typography.h5,
        )
        Text(
            text = DateUtil.getDate(DateUtil.parseId(picture.id.date)),
            style = MaterialTheme.typography.body2
        )
        Row {
            Text(
                text = stringResource(id = R.string.copyright),
                style = MaterialTheme.typography.body2
            )
            Text(
                modifier = Modifier
                    .onLongClick {
                        context.copyToClipboard(picture.copyright)
                    },
                text = picture.copyright ?: "",
                style = MaterialTheme.typography.body2,
            )
        }
        Text(
            text = stringResource(id = R.string.explanation),
            style = MaterialTheme.typography.body2
        )
        Text(
            modifier = Modifier
                .onLongClick {
                    context.copyToClipboard(picture.explanation)
                },
            text = picture.explanation ?: "",
            style = MaterialTheme.typography.body2,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun Modifier.onLongClick(action: () -> Unit) = apply {
    this.combinedClickable(
        onClick = { },
        onLongClick = action,
    )
}

private fun Context.copyToClipboard(
    text: String?
) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager?
    val clip: ClipData = ClipData.newPlainText("", text)
    clipboard?.setPrimaryClip(clip)

    Toast.makeText(this, getString(R.string.copied_to_clipboard), Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun PictureDescriptionPreview() {
    AstronomyPicturesTheme {
        PictureDescription(
            modifier = Modifier,
            pictureUiState = PictureUiState.Success(
                picture = Picture(
                    id = Date().toId(),
                    title = "The Cat's Eye Nebula in Optical and X-ray ",
                    explanation = " To some it looks like a cat's eye. To others, perhaps like a giant cosmic conch shell. It is actually one of the brightest and most highly detailed planetary nebula known, composed of gas expelled in the brief yet glorious phase near the end of life of a Sun-like star. This nebula's dying central star may have produced the outer circular concentric shells by shrugging off outer layers in a series of regular convulsions. The formation of the beautiful, complex-yet-symmetric inner structures, however, is not well understood. The featured image is a composite of a digitally sharpened Hubble Space Telescope image with X-ray light captured by the orbiting Chandra Observatory. The exquisite floating space statue spans over half a light-year across. Of course, gazing into this Cat's Eye, humanity may well be seeing the fate of our sun, destined to enter its own planetary nebula phase of evolution ... in about 5 billion years. ",
                    copyright = "Rudy Pohl",
                    source = by.radiance.space.pictures.domain.entity.Image(huge = "", light = ""),
                    isSaved = false
                )

            ),
        )
    }
}