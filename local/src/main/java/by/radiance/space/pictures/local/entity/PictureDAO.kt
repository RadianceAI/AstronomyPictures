package by.radiance.space.pictures.local.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface PictureDAO {
    @Query("""SELECT * FROM picture""")
    suspend fun getAll(): List<Picture>

    @Query("""SELECT * FROM picture WHERE id = :id""")
    suspend fun getPicture(id: Date): List<Picture>

    @Insert
    suspend fun insert(picture: Picture)

    @Query("""DELETE FROM picture WHERE id = :id""")
    suspend fun delete(id: Date)
}