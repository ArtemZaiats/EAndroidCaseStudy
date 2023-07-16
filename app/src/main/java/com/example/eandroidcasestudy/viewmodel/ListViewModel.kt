package com.example.eandroidcasestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eandroidcasestudy.model.Device
import com.example.eandroidcasestudy.model.DevicesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(private val repository: DevicesRepository = DevicesRepository.getInstance()) :
    ViewModel() {

    private var job: Job? = null

    val devices = MutableLiveData<List<Device>>()
    val loading = MutableLiveData<Boolean>()

    init {
        onLoading()

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = getDevices()
            withContext(Dispatchers.Main) {
                devices.value = response
                loading.value = false
            }
        }
    }

    private suspend fun getDevices(): List<Device> {
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