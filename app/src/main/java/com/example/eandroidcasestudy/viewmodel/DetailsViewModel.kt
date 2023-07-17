package com.example.eandroidcasestudy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.eandroidcasestudy.model.Device
import com.example.eandroidcasestudy.room.DeviceDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { DeviceDatabase(getApplication()).deviceDao() }

    var device = MutableLiveData<Device>()

    fun getDeviceById(id: Long) {
        coroutineScope.launch {
            val currentDevice = db.fetchDeviceById(id)
            withContext(Dispatchers.Main) {
                device.value = currentDevice
            }
        }
    }

    fun updateDevice(homeName: String, id: Long) {
        coroutineScope.launch {
            db.update(homeName, id)
            getDeviceById(id)
        }
    }
}