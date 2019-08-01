package com.huxy.samplevet.data.offline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.huxy.samplevet.data.offline.dao.CoordinatesDao

@Database(entities = [Coordinates::class], version = 1)
abstract class CoordinatesDatabase : RoomDatabase() {

    abstract fun coordinatesDao(): CoordinatesDao

    companion object {

        @Volatile
        private var INSTANCE: CoordinatesDatabase? = null

        fun getDatabase(context: Context): CoordinatesDatabase? {
            if (INSTANCE == null) {
                synchronized(CoordinatesDatabase::class.java) {
                    if (INSTANCE == null) {

                        // Create database here
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CoordinatesDatabase::class.java, "coordinates_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
