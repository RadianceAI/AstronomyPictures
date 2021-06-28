package by.radiance.space.pictures.today.repository
import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import by.radiance.space.pictures.domain.entity.AstronomyPicture
import by.radiance.space.pictures.domain.entity.Image
import by.radiance.space.pictures.domain.entity.PictureId
import by.radiance.space.pictures.domain.repository.today.TodayAstronomyPictureRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*

class DataStoreTodayAstronomyPictureRepository(
    private val dataStore: DataStore<Preferences>
): TodayAstronomyPictureRepository {
    private var todayPicture: AstronomyPicture? = null

    override suspend fun get(): AstronomyPicture? {
        return dataStore.data.map { preferences ->
            val id = preferences[ID_KEY]
            if (id == null) {
                null
            } else {
                AstronomyPicture(
                    id = PictureId(getDate(id)),
                    title = preferences[TITLE_KEY],
                    explanation = preferences[EXPLANATION_KEY],
                    copyright = preferences[COPYRIGHT_KEY],
                    source = Image(light = preferences[SRC_KEY]?: "", huge = preferences[HSRC_KEY]?:""),
                    isSaved = preferences[IS_SAVED_KEY]?:false
                )
            }
        }.first()
    }

    override suspend fun save(picture: AstronomyPicture): AstronomyPicture {
        dataStore.edit { preference ->
            preference[ID_KEY] = getDate(picture.id.date)
            preference[TITLE_KEY] = picture.title?:""
            preference[EXPLANATION_KEY] = picture.explanation?:""
            preference[COPYRIGHT_KEY] = picture.copyright?:""

            val source = picture.source

            preference[SRC_KEY] = if (source is Image) source.light else ""
            preference[HSRC_KEY] = if (source is Image) source.huge else ""
            preference[IS_SAVED_KEY] = picture.isSaved
        }
        return picture
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(string: String): Date {
        val simpleDate = SimpleDateFormat("yyyy-MM-dd")
        return simpleDate.parse(string)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(date: Date): String {
        val simpleDate = SimpleDateFormat("yyyy-MM-dd")
        return simpleDate.format(date)
    }

    companion object {
        private val ID_KEY = stringPreferencesKey("id_preference_key")
        private val TITLE_KEY = stringPreferencesKey("title_preference_key")
        private val EXPLANATION_KEY = stringPreferencesKey("explanation_preference_key")
        private val COPYRIGHT_KEY = stringPreferencesKey("copyright_preference_key")
        private val SRC_KEY = stringPreferencesKey("src_preference_key")
        private val HSRC_KEY = stringPreferencesKey("hsrc_preference_key")
        private val IS_SAVED_KEY = booleanPreferencesKey("is_saved_preference_key")
    }
}