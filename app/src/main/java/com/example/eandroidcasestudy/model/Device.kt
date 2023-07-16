package com.example.eandroidcasestudy.model

import com.google.gson.annotations.SerializedName

data class DevicesResponse(@SerializedName("Devices") val devices: List<Device>)

data class Device(
    @SerializedName("PK_Device") val pkDevice: Int,

    @SerializedName("MacAddress") val macAddress: String,

    @SerializedName("PK_DeviceType") val pkDeviceType: Int,

    @SerializedName("PK_DeviceSubType") val pkDeviceSubType: Int,

    @SerializedName("Firmware") val firmware: String,

    @SerializedName("Server_Device") val serverDevice: String,

    @SerializedName("Server_Event") val serverEvent: String,

    @SerializedName("Server_Account") val serverAccount: String,

    @SerializedName("InternalIP") val internalIp: String,

    @SerializedName("LastAliveReported") val lastAliveReported: String,

    @SerializedName("Platform") val platform: String
)
