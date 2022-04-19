package by.radiance.space.picrures.util.di

import by.radiance.space.picrures.util.repository.ShareImageRepository
import by.radiance.space.pictures.domain.repository.ShareRepository
import org.koin.dsl.module

val utilModule = module {
    single { ShareImageRepository(get()) as ShareRepository }
}