package by.radiance.space.pictures.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(
        tableName = "picture"
)
data class AstronomyPicture(
        @PrimaryKey @ColumnInfo(name = "id") val id: Date,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "explanation") val explanation: String?,
        @ColumnInfo(name = "copyright") val copyright: String?,
        @ColumnInfo(name = "type") val type: SourceType,
        @ColumnInfo(name = "src") val src: String?,
        @ColumnInfo(name = "hsrc") val hsrc: String?,
        @ColumnInfo(name = "save_date") val saveDate: Date?,
)