package com.example.eandroidcasestudy.view

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.eandroidcasestudy.R
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

        setupActionBar()
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        val deviceId = arguments?.getLong("deviceId") ?: -1
        viewModel.getDeviceById(deviceId)

        observeViewModel()

        binding.editImage.setOnClickListener { setEditable() }

        binding.saveImage.setOnClickListener { saveChanges(deviceId) }

    }

    private fun setupActionBar() {
        binding.toolbarDetailFragment.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolbarDetailFragment.setNavigationOnClickListener {
            (activity as AppCompatActivity).onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun observeViewModel() {
        viewModel.device.observe(viewLifecycleOwner) { device ->
            device?.let {
                binding.apply {
                    deviceImage.setImage(device)
                    homeName.text = device.homeName
                    editHomeName.setText(device.homeName)
                    deviceSN.text = "SN: ${device.pkDevice}"
                    model.text = "Model: ${device.platform}"
                    firmware.text = "Firmware: ${device.firmware}"
                    macAddress.text = "MAC Address: ${device.macAddress}"
                }
            }
        }
    }

    private fun saveChanges(deviceId: Long) {
        val newName = binding.editHomeName.text
        if (newName.isEmpty()) {
            Toast.makeText(activity, "Please fill new name", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.updateDevice(newName.toString(), deviceId)
        }
        binding.apply {
            editHomeName.visibility = View.GONE
            homeName.visibility = View.VISIBLE
            saveImage.visibility = View.GONE
            editImage.visibility = View.VISIBLE
        }
    }

    private fun setEditable() {
        binding.apply {
            homeName.visibility = View.GONE
            editHomeName.visibility = View.VISIBLE
            editImage.visibility = View.GONE
            saveImage.visibility = View.VISIBLE
        }
    }
}