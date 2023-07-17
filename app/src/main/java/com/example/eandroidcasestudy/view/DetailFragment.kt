package com.example.eandroidcasestudy.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.eandroidcasestudy.databinding.FragmentDetailBinding
import com.example.eandroidcasestudy.viewmodel.DetailsViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        val deviceId = arguments?.getLong("deviceId") ?: -1
        Log.d("DetailFragment", deviceId.toString())
        viewModel.getDeviceById(deviceId)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.device.observe(viewLifecycleOwner) { device ->
            device?.let {
                binding.deviceImage.setImage(device)
                binding.homeName.text = device.homeName
                binding.deviceSN.text = "SN: ${device.pkDevice}"
                binding.model.text = "Model: ${device.platform}"
                binding.firmware.text = "Firmware: ${device.firmware}"
                binding.macAddress.text = "MAC Address: ${device.macAddress}"
            }
        }
    }
}