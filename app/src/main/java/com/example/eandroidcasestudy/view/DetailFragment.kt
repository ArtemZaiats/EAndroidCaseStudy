package com.example.eandroidcasestudy.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.eandroidcasestudy.databinding.FragmentDetailBinding
import com.example.eandroidcasestudy.viewmodel.ListViewModel

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val deviceId = arguments?.getInt("deviceId") ?: -1
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