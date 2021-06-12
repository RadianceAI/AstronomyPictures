package by.radiance.space.picrures.application

import android.app.Application
import by.radiance.space.pictures.data.di.remote
import by.radiance.space.pictures.data.di.today
import by.radiance.space.pictures.data.di.token
import by.radiance.space.pictures.domain.di.usecase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(listOf(
                remote,
                today,
                token,
                usecase,
            ))
        }
    }
}