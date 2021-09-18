package by.radiance.space.pictures.local.converter

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import by.radiance.space.pictures.local.entity.SourceType
import java.sql.Date

@TypeConverters
class SqlConverter {
    @TypeConverter
    fun getDate(timestamp: Long?): Date? {
        return timestamp?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun getDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun getSourceType(id: Int): SourceType {
        return SourceType.getById(id)
    }

    @TypeConverter
    fun getSourceType(type: SourceType): Int {
        return type.id
    }
}