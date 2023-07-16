package com.example.eandroidcasestudy.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eandroidcasestudy.R
import com.example.eandroidcasestudy.databinding.ItemDeviceBinding
import com.example.eandroidcasestudy.model.Device

class DeviceListAdapter(var devicesList: ArrayList<Device>): RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>() {

    private lateinit var binding: ItemDeviceBinding

    fun updateDevices(newDevices: List<Device>) {
        devicesList.clear()
        devicesList.addAll(newDevices)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        binding = ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding)
    }

    override fun getItemCount() = devicesList.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(devicesList[position])
    }

    class DeviceViewHolder(binding: ItemDeviceBinding): RecyclerView.ViewHolder(binding.root) {

        private val imageView = binding.ivDeviceImage
        private val homeName = binding.tvHomeName
        private val deviceNumber = binding.tvDeviceSN

        fun bind(device: Device) {
            homeName.text = "Home1"
            "SN: ${device.pkDevice}".also { deviceNumber.text = it }
            setImage(device.platform)
        }

        private fun setImage(platform: String?) {
            when(platform) {
                "Sercomm G450" -> { imageView.setImageResource(R.drawable.vera_plus_big) }
                "Sercomm G550" -> { imageView.setImageResource(R.drawable.vera_secure_big) }
                "MiCasaVerde VeraLite" -> { imageView.setImageResource(R.drawable.vera_edge_big) }
                "Sercomm NA900" -> { imageView.setImageResource(R.drawable.vera_edge_big) }
                "Sercomm NA301" -> { imageView.setImageResource(R.drawable.vera_edge_big) }
                "Sercomm NA930" -> { imageView.setImageResource(R.drawable.vera_edge_big) }
               else -> { imageView.setImageResource(R.drawable.vera_edge_big) }
            }
        }
    }




}