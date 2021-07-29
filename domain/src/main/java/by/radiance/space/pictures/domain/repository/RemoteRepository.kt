package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.Picture
import java.util.*

interface RemoteRepository {
    suspend fun getPicture(date: Date): Picture
    suspend fun getRandomPicture(count: Int): List<Picture>
}