package by.radiance.space.picrures.util.work

import android.content.Context
import android.util.Log
import androidx.work.*
import by.radiance.space.pictures.domain.usecase.GetTodayPictureUseCase
import by.radiance.space.pictures.domain.usecase.SetWallpaperUseCase
import by.radiance.space.pictures.domain.utils.DateHelper
import by.radiance.space.pictures.domain.utils.LoadingState
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.io.OutputStreamWriter
import java.util.*
import java.util.concurrent.TimeUnit

class WallpaperWorker(
    private val appContext: Context,
    workerParameters: WorkerParameters,
    private val todayPictureUseCase: GetTodayPictureUseCase,
    private val wallpaperUseCase: SetWallpaperUseCase,
) : CoroutineWorker(appContext, workerParameters) {

    override suspend fun doWork(): Result {
        writeToFile("${DateHelper.getDate(Date(), "HH:mm:ss\n")}", appContext)
        todayPictureUseCase.get()
            .onEach { picture ->
                when (picture) {
                    is LoadingState.Success -> {
                        val loader = ImageLoader(appContext)
                        val request = ImageRequest.Builder(appContext)
                            .data(picture.picture.source.huge)
                            .allowHardware(false)
                            .build()

                        val result = (loader.execute(request) as SuccessResult).drawable
                        wallpaperUseCase.setAllWallpaper(result)

                        start(appContext, DateHelper.tomorrow(), true)
                    }
                    else -> Unit
                }
            }
            .take(1)
            .collect()

        return Result.success()
    }

    private fun writeToFile(data: String, context: Context) {
        try {
            val outputStreamWriter =
                OutputStreamWriter(context.openFileOutput("log", Context.MODE_APPEND))
            outputStreamWriter.append(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e("Exception", "File write failed: $e")
        }
    }

    companion object {
        private const val TAG = "Wallpaper"

        fun start(context: Context, startTime: Date = Date(), replace: Boolean = false) {

            val start = Calendar.getInstance().apply { time = startTime }.get(Calendar.HOUR_OF_DAY).let { if (it == 0) 24 else it }
            val now = Calendar.getInstance().apply { time = Date() }.get(Calendar.HOUR_OF_DAY)

            val uploadWorkRequest: PeriodicWorkRequest =
                PeriodicWorkRequestBuilder<WallpaperWorker>(1, TimeUnit.HOURS)
                    .apply {
                        if ((start - now) != 0)
                            setInitialDelay(start - now.toLong(), TimeUnit.HOURS)
                    }
                    .build()

            WorkManager
                .getInstance(context)
                .enqueueUniquePeriodicWork(
                    TAG,
                    if (replace) ExistingPeriodicWorkPolicy.REPLACE else ExistingPeriodicWorkPolicy.KEEP,
                    uploadWorkRequest
                )
        }
    }
}