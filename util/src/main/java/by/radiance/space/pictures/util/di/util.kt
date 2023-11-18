package by.radiance.space.pictures.util.di

import by.radiance.space.pictures.util.repository.SetWallpaperRepository
import by.radiance.space.pictures.util.repository.ShareImageRepository
import by.radiance.space.pictures.util.work.WallpaperWorker
import by.radiance.space.pictures.domain.repository.WallpaperRepository
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val utilModule = module {
    single { ShareImageRepository(get()) }
    single { SetWallpaperRepository(get()) as WallpaperRepository }
    worker { WallpaperWorker(get(), get(), get(), get()) }
}