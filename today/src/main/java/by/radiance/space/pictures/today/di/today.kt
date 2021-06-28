package by.radiance.space.pictures.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import by.radiance.space.pictures.domain.repository.today.TodayAstronomyPictureRepository
import by.radiance.space.pictures.today.repository.DataStoreTodayAstronomyPictureRepository
import org.koin.dsl.module



val today = module {
    single { DataStoreTodayAstronomyPictureRepository(get()) as TodayAstronomyPictureRepository }
}