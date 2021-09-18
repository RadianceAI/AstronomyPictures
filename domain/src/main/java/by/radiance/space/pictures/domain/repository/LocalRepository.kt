package by.radiance.space.pictures.domain.repository

import by.radiance.space.pictures.domain.entity.Picture
import kotlinx.coroutines.flow.Flow
import java.util.*

interface LocalRepository {
    fun getAll(): Flow<List<Picture>>
    fun find(startDate: Date, endDate: Date): Flow<List<Picture>>
    fun getPicture(data: Date): Flow<Picture>
    suspend fun save(picture: Picture): Picture
    suspend fun delete(picture: Picture)
}