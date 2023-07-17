package com.example.eandroidcasestudy.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eandroidcasestudy.databinding.ItemDeviceBinding
import com.example.eandroidcasestudy.model.Device

class DeviceListAdapter(
    private var devicesList: ArrayList<Device>,
    private val listener: DeviceClickListener
) : RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>() {

    private lateinit var binding: ItemDeviceBinding

    interface DeviceClickListener {
        fun onItemClick(device: Device)
        fun onItemLongClick(device: Device)
    }

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

    inner class DeviceViewHolder(binding: ItemDeviceBinding) : RecyclerView.ViewHolder(binding.root) {

        private val imageView = binding.ivDeviceImage
        private val homeName = binding.tvHomeName
        private val deviceNumber = binding.tvDeviceSN

        private val view = binding.root

        fun bind(device: Device) {
            homeName.text = device.homeName
            deviceNumber.text = "SN: ${device.pkDevice}"
            imageView.setImage(device)

            view.setOnClickListener { listener.onItemClick(device) }
            view.setOnLongClickListener {
                listener.onItemLongClick(device)
                true
            }
        }

    }
}