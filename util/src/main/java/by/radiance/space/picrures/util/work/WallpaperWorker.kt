package by.radiance.space.picrures.util.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class WallpaperWorker(
    appContext: Context,
    workerParameters: WorkerParameters,
) : Worker(appContext, workerParameters) {

    override fun doWork(): Result {
        Log.d("worker", "doWork: test")
        return Result.success()
    }
}