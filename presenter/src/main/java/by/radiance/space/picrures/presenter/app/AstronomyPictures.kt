package by.radiance.space.picrures.presenter.app

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import by.radiance.space.pictures.data.di.remote
import by.radiance.space.pictures.data.di.today
import by.radiance.space.pictures.data.di.token
import by.radiance.space.pictures.domain.di.usecase
import by.radiance.space.pictures.local.di.local
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AstronomyPictures: Application() {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AstronomyPictures)

            modules(
                listOf(
                    remote,
                    local,
                    today,
                    token,
                    usecase,
                    module {
                        single { dataStore }
                    }
                )
            )
        }
    }
}