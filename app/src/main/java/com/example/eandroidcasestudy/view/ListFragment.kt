package com.example.eandroidcasestudy.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eandroidcasestudy.R
import com.example.eandroidcasestudy.databinding.FragmentListBinding
import com.example.eandroidcasestudy.model.Device
import com.example.eandroidcasestudy.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var viewModel: ListViewModel
    private lateinit var devicesAdapter: DeviceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        observeViewModel()

        devicesAdapter = DeviceListAdapter(arrayListOf(), object : DeviceListAdapter.DeviceClickListener {
            override fun onItemClick(device: Device) {
                val bundle = bundleOf("deviceId" to device.id)
                findNavController().navigate(R.id.actionGoToDetails, bundle)
            }

            override fun onItemLongClick(device: Device) {
                showDeleteDialog(device)
            }
        })

        binding.rvDevicesList.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = devicesAdapter
        }



    }

    private fun observeViewModel() {
        viewModel.devices.observe(viewLifecycleOwner) { devices ->
            devices?.let {
                binding.rvDevicesList.visibility = View.VISIBLE
                devicesAdapter.updateDevices(it)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                binding.loading.visibility = if (isLoading) View.VISIBLE else View.GONE
                if (isLoading) {
                    binding.rvDevicesList.visibility = View.GONE
                }
            }
        }
    }

    private fun showDeleteDialog(device: Device) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setMessage("Are you sure you want to delete this item?")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                viewModel.deleteDeviceById(device.id)
                viewModel.devices.value?.let { devicesAdapter.updateDevices(it) }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = dialogBuilder.create()
        alert.show()
    }

}