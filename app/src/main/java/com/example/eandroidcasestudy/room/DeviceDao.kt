package com.example.eandroidcasestudy.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.eandroidcasestudy.model.Device

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(device: Device)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(devices: List<Device>)

    @Update
    suspend fun update(device: Device)

    @Delete
    suspend fun delete(device: Device)

    @Query("DELETE FROM `device-table` WHERE id = :id")
    suspend fun deleteDeviceById(id: Long)

    @Query("SELECT * FROM `device-table`")
    fun fetchAllDevices(): List<Device>

    @Query("SELECT * FROM `device-table` WHERE id = :id")
    suspend fun fetchDeviceById(id: Int): Device
}