package com.example.eandroidcasestudy.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DevicesService {

    private val BASE_URL = "https://veramobile.mios.com/"
    private var api: DevicesApi

    init {
         val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(DevicesApi::class.java)

    }

    suspend fun getDevices(): DevicesResponse {
        return api.getDevices()
    }

}