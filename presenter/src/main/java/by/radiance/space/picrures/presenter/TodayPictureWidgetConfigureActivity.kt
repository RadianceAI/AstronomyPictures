package by.radiance.space.picrures.presenter

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import by.radiance.space.picrures.presenter.databinding.TodayPictureWidgetConfigureBinding
import by.radiance.space.picrures.presenter.utils.onProgressChanged
import by.radiance.space.pictures.domain.usecase.GetRandomPictureUseCase
import by.radiance.space.pictures.domain.usecase.GetAstronomyPicturesUseCase
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import coil.transform.RoundedCornersTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TodayPictureWidgetConfigureActivity : Activity(), KoinComponent {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val todayPictureUseCase: GetAstronomyPicturesUseCase by inject()
    private val randomPictureUseCase: GetRandomPictureUseCase by inject()

    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private lateinit var binding: TodayPictureWidgetConfigureBinding
    private var cornerRadius: Float = 0f
    private var source: Source = Source.Today

    private var onClickListener = View.OnClickListener {
        val context = this@TodayPictureWidgetConfigureActivity

        saveCornerSizePref(applicationContext, appWidgetId, cornerRadius)
        saveSourcePref(applicationContext, appWidgetId, source)

        val appWidgetManager = AppWidgetManager.getInstance(context)
        updateAppWidget(
            coroutineScope,
            todayPictureUseCase,
            randomPictureUseCase,
            context,
            appWidgetManager,
            appWidgetId,
            cornerRadius,
            source
        )

        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        setResult(RESULT_CANCELED)

        binding = TodayPictureWidgetConfigureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener(onClickListener)
        binding.seekBar.onProgressChanged { progress, _ -> setUpCornerRadius(progress) }
        binding.today.isChecked = true
        binding.today.setOnCheckedChangeListener { _, isChecked -> if (isChecked) source = Source.Today }
        binding.random.setOnCheckedChangeListener { _, isChecked -> if (isChecked) source = Source.Random }

        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }
    }

    private fun setUpCornerRadius(progress: Int) {
        cornerRadius = progress.toFloat()

        val loader = ImageLoader(applicationContext)
        val request = ImageRequest.Builder(applicationContext)
            .data(R.drawable.place_holder)
            .allowHardware(false)
            .transformations(RoundedCornersTransformation(progress.toFloat()))
            .build()

        coroutineScope.launch {
            val result = (loader.execute(request) as SuccessResult).drawable
            withContext(Dispatchers.Main) {
                binding.preview.setImageBitmap((result as BitmapDrawable).bitmap)
            }
        }
    }
}

private const val PREFS_NAME = "by.radiance.space.picrures.presenter.TodayPictureWidget"
private const val CORNER_SIZE_KEY = "appwidget_corner_size"
private const val SOURCE_KEY = "appwidget_source"

internal fun saveCornerSizePref(context: Context, appWidgetId: Int, size: Float) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.putFloat(CORNER_SIZE_KEY + appWidgetId, size)
    prefs.apply()
}
internal fun loadCornerSizePref(context: Context, appWidgetId: Int): Float {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
    return prefs.getFloat(CORNER_SIZE_KEY + appWidgetId, 0f)
}

internal fun deleteCornerSizePref(context: Context, appWidgetId: Int) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.remove(CORNER_SIZE_KEY + appWidgetId)
    prefs.apply()
}

internal fun saveSourcePref(context: Context, appWidgetId: Int, source: Source) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.putInt(SOURCE_KEY + appWidgetId, source.id)
    prefs.apply()
}
internal fun loadSourcePref(context: Context, appWidgetId: Int): Source {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
    return Source.fromId(prefs.getInt(SOURCE_KEY + appWidgetId, 0))
}

internal fun deleteSourcePref(context: Context, appWidgetId: Int) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.remove(SOURCE_KEY + appWidgetId)
    prefs.apply()
}
