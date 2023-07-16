package com.example.eandroidcasestudy.model

import retrofit2.http.GET

interface DevicesApi {

    @GET("test_android/items.test")
    suspend fun getDevices(): DevicesResponse
}