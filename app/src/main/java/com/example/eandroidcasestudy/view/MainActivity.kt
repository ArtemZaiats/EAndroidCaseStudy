package com.example.eandroidcasestudy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eandroidcasestudy.databinding.ActivityMainBinding
import com.example.eandroidcasestudy.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ListViewModel
    private val devicesAdapter = DeviceListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        binding.rvDevicesList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = devicesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.devices.observe(this) { devices ->
            devices?.let {
                binding.rvDevicesList.visibility = View.VISIBLE
                devicesAdapter.updateDevices(it)
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                binding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading) {
                    binding.rvDevicesList.visibility = View.GONE
                }
            }
        }
    }


}