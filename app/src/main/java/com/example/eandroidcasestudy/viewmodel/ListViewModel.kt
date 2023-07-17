package com.example.eandroidcasestudy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.eandroidcasestudy.model.Device
import com.example.eandroidcasestudy.model.DevicesRepository
import com.example.eandroidcasestudy.room.DeviceDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private var job: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val db by lazy { DeviceDatabase(getApplication()).deviceDao() }
    private val repository: DevicesRepository = DevicesRepository.getInstance()

    val devices = MutableLiveData<List<Device>>()
    val loading = MutableLiveData<Boolean>()

    fun deleteDeviceById(id: Long) {
        coroutineScope.launch {
            db.deleteDeviceById(id)
            getAllDevices()
        }
    }

    init {
        onLoading()

        job = coroutineScope.launch {
            val response = getResponse()
            if (response.isNotEmpty()) {
                saveDevices(response)
            } else {
                delay(300L)
                saveDevices(response)
            }
            getAllDevices()
        }
    }

    private fun getAllDevices() {
        coroutineScope.launch {
            val allDevices = db.fetchAllDevices()
            withContext(Dispatchers.Main) {
                devices.value = allDevices
                loading.value = false
            }
        }
    }

    private suspend fun saveDevices(devices: List<Device>) {
        val cachedDevices = devices
        var index = 1
        cachedDevices.forEach {
            it.homeName = "Home Name ${index++}"
        }
        coroutineScope.launch {
            db.insertAll(cachedDevices)
        }
    }

    private suspend fun getResponse(): List<Device> {
        return repository.getDevices().devices
    }

    private fun onLoading() {
        loading.value = true
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}