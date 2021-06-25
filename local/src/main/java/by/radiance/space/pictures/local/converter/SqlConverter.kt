package by.radiance.space.pictures.local.converter

import androidx.room.TypeConverter
import androidx.room.TypeConverters
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
}