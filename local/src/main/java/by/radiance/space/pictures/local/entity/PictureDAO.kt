package by.radiance.space.pictures.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Dao
interface PictureDAO {
    @Query("""SELECT * FROM picture ORDER BY id DESC""")
    fun getAll(): Flow<List<AstronomyPicture>>

    @Query("""SELECT * FROM picture WHERE id BETWEEN :startDate AND :endDate""")
    suspend fun getPictures(startDate: Date, endDate: Date): List<AstronomyPicture>

    @Query("""SELECT * FROM picture WHERE id = :id""")
    fun getPicture(id: Date): Flow<AstronomyPicture?>

    @Insert
    suspend fun insert(astronomyPicture: AstronomyPicture)

    @Query("""DELETE FROM picture WHERE id = :id""")
    suspend fun delete(id: Date)
}