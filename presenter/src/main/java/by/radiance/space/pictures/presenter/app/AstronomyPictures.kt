package by.radiance.space.pictures.presenter.app

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import by.radiance.space.pictures.presenter.di.viewModel
import by.radiance.space.pictures.util.di.utilModule
import by.radiance.space.pictures.data.di.remote
import by.radiance.space.pictures.data.di.token
import by.radiance.space.pictures.domain.di.usecase
import by.radiance.space.pictures.local.di.local
import by.radiance.space.preferencies.di.settings
import by.radiance.space.source.di.source
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AstronomyPictures: Application(), KoinComponent {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AstronomyPictures)

            workManagerFactory()

            modules(listOf(
                remote,
                local,
                token,
                usecase,
                settings,
                utilModule,
                viewModel,
                source,
                module { single { dataStore } }
            ))
        }
    }
}