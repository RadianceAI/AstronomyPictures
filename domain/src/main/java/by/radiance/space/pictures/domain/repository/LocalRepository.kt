package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.Picture
import kotlinx.coroutines.flow.Flow
import java.util.*

interface LocalRepository {
    suspend fun getAll(): Flow<List<Picture>>
    suspend fun find(startDate: Date, endDate: Date): Flow<List<Picture>>
    suspend fun getPicture(data: Date): Picture
    suspend fun save(picture: Picture): Picture
    suspend fun delete(picture: Picture): Picture
}