package com.example.eandroidcasestudy.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eandroidcasestudy.model.Device

@Database(entities = [Device::class], version = 1)
abstract class DeviceDatabase: RoomDatabase() {

    abstract fun deviceDao(): DeviceDao

    companion object {
        @Volatile private var instance: DeviceDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DeviceDatabase::class.java,
            "devicedatabase"
        ).build()
    }
}