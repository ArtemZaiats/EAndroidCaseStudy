package com.example.eandroidcasestudy.view

import com.example.eandroidcasestudy.R
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.eandroidcasestudy.model.Device

fun ImageView.setImage(device: Device) {
    val uri: Int?
    when (device.platform) {
        "Sercomm G450" -> {
            uri = R.drawable.vera_plus_big
        }
        "Sercomm G550" -> {
            uri = R.drawable.vera_secure_big
        }
        "MiCasaVerde VeraLite" -> {
            uri = R.drawable.vera_edge_big
        }
        "Sercomm NA900" -> {
            uri = R.drawable.vera_edge_big
        }
        "Sercomm NA301" -> {
            uri = R.drawable.vera_edge_big
        }
        "Sercomm NA930" -> {
            uri = R.drawable.vera_edge_big
        }
        else -> {
            uri = R.drawable.vera_edge_big
        }
    }

    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}