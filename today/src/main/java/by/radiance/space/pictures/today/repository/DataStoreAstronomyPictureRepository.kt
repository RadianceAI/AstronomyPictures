package by.radiance.space.pictures.today.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import by.radiance.space.pictures.domain.entity.Picture
import by.radiance.space.pictures.today.entity.PictureList
import by.radiance.space.pictures.today.mapper.PictureMapper
import by.radiance.space.pictures.today.utils.DateUtils
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class DataStoreAstronomyPictureRepository(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun getAll(): Flow<List<Picture>> {
        deleteOldPictures()

        return dataStore.data.map { preference ->
            val listJson = preference[PICTURES_KEY] ?: return@map emptyList()

            val pictures = Gson().fromJson(listJson, PictureList::class.java)

            pictures.list
                .map { picture ->
                    PictureMapper().map(picture)
                }
        }
    }

    private suspend fun deleteOldPictures() {
        dataStore.edit { preference ->
            val listJson = preference[PICTURES_KEY]

            val pictures = if (listJson == null) {
                PictureList(mutableListOf())
            } else {
                Gson().fromJson(listJson, PictureList::class.java)
            }

            val newList = pictures.list
                .filter {
                    if (it.date == null) return@filter false
                    DateUtils.getDate(it.date).time == DateUtils.getDate(DateUtils.getDate(Date())).time
                }

            pictures.list.clear()
            pictures.list.addAll(newList)

            preference[PICTURES_KEY] = Gson().toJson(pictures)
        }
    }

    suspend fun save(picture: Picture) {
        dataStore.edit { preference ->
            val listJson = preference[PICTURES_KEY]

            val pictures = if (listJson == null) {
                PictureList(mutableListOf())
            } else {
                Gson().fromJson(listJson, PictureList::class.java)
            }

            pictures.list.add(PictureMapper().map(picture).copy(date = DateUtils.getDate(Date())))

            preference[PICTURES_KEY] = Gson().toJson(pictures)
        }
    }

    companion object {
        private val PICTURES_KEY = stringPreferencesKey(name = "list_key")
    }
}