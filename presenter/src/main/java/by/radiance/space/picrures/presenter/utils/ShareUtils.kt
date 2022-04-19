package by.radiance.space.picrures.presenter.utils

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

object ShareUtils {

    fun shareDrawable(
        drawableToShare: Drawable,
        context: Context
    ) {
        val bitmap: Bitmap = (drawableToShare as BitmapDrawable).bitmap

        val imagePath = File(context.filesDir, "images/")
        imagePath.mkdir()
        val newFile = File(imagePath.path, "share.jpg")

        val os: OutputStream = BufferedOutputStream(FileOutputStream(newFile))
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
        os.close();

        val contentUri: Uri =
            FileProvider.getUriForFile(context, "by.radiance.space.pictures", newFile)

        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, contentUri)
            type = "image/*"
            clipData = ClipData.newRawUri("", contentUri)
            flags =
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        }

        context.startActivity(Intent.createChooser(shareIntent, null))
    }
}