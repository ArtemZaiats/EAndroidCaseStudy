package com.example.eandroidcasestudy.model

class DevicesRepository(private val webService: DevicesService = DevicesService()) {

    private var cachedDevices = listOf<Device>()
    suspend fun getDevices(): DevicesResponse {
        val response = webService.getDevices()
        cachedDevices = response.devices
        return response
    }

    companion object {
        @Volatile
        private var instance: DevicesRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DevicesRepository().also { instance = it }
        }
    }
}