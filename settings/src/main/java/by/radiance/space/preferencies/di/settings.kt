package by.radiance.space.preferencies.di

import by.radiance.space.pictures.domain.repository.SettingRepository
import by.radiance.space.preferencies.DataStoreSettingsRepository
import org.koin.dsl.module

val settings = module {
    single<SettingRepository> { DataStoreSettingsRepository(get()) }
}
