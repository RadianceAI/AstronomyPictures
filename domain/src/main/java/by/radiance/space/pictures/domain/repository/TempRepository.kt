package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.Picture
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TempRepository {
    suspend fun getAll(): Flow<List<Picture>>
    suspend fun getPicture(date: Date): Flow<Picture>
    suspend fun save(picture: Picture): Picture
}