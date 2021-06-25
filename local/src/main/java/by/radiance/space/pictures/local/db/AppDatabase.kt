package by.radiance.space.pictures.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import by.radiance.space.pictures.local.converter.SqlConverter
import by.radiance.space.pictures.local.entity.Picture
import by.radiance.space.pictures.local.entity.PictureDAO


@TypeConverters(SqlConverter::class)
@Database(
    version = 1,
    exportSchema = false,
    entities =
    [
        Picture::class
    ]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun pictureDao(): PictureDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        private var DB_NAME = "pictures"

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        protected fun buildDatabase(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .addMigrations(

                )
                .build()
        }

        fun clear() {

            INSTANCE?.clearAllTables()
        }
    }
}