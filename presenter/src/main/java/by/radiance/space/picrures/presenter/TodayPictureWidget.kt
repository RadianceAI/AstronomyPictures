package by.radiance.space.picrures.presenter

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.RemoteViews
import by.radiance.space.pictures.domain.usecase.GetAstronomyPicturesUseCase
import by.radiance.space.pictures.domain.utils.LoadingState
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.Date

class TodayPictureWidget : AppWidgetProvider(), KoinComponent {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val todayPictureUseCase: GetAstronomyPicturesUseCase by inject()

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            Log.d("widget", "onUpdate: 123")

            val cornerRadius = loadCornerSizePref(context, appWidgetId)
            val source = loadSourcePref(context, appWidgetId)

            updateAppWidget(
                coroutineScope,
                todayPictureUseCase,
                context,
                appWidgetManager,
                appWidgetId,
                cornerRadius,
            )
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            deleteCornerSizePref(context, appWidgetId)
            deleteSourcePref(context, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
    }

    override fun onDisabled(context: Context) {
    }
}

internal fun updateAppWidget(
    scope: CoroutineScope,
    todayPictureUseCase: GetAstronomyPicturesUseCase,
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    cornerRadius: Float,
) {
    val views = RemoteViews(context.packageName, R.layout.today_picture_widget)

    scope.launch {
        todayPictureUseCase.get(Date(), Date())
            .onEach { picture ->
                when (picture) {
                    is LoadingState.Success -> {
                        val loader = ImageLoader(context)
                        val request = ImageRequest.Builder(context)
                            .data(picture.data.first().source.light)
                            .allowHardware(false)
                            .transformations(RoundedCornersTransformation(cornerRadius))
                            .build()

                        val result = (loader.execute(request) as SuccessResult).drawable
                        views.setImageViewBitmap(
                            R.id.widget_image,
                            (result as BitmapDrawable).bitmap
                        )
                    }

                    else -> Unit
                }
            }
            .take(1)
            .collect()

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}

enum class Source(val id: Int) {
    Today(0),
    Random(1);

    companion object {
        fun fromId(id: Int): Source {
            return values().find { it.id == id } ?: Today
        }
    }
}