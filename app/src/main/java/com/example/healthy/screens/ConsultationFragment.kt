package com.example.healthy.screens

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.healthy.R
import com.example.healthy.adapter.SymptomsAdapter
import com.example.healthy.base.BaseFragment
import com.example.healthy.databinding.ConsultationFragmentBinding
import com.example.healthy.vm.ConsultationViewModel

class ConsultationFragment :
    BaseFragment<ConsultationFragmentBinding>(ConsultationFragmentBinding::inflate) {

    private val viewModel: ConsultationViewModel by viewModels()

    private lateinit var symptomsAdapter: SymptomsAdapter

    override fun setUp() {
        setupSymptomsAdapter()
    }

    override fun listeners() {
        binding.btnDetect.setOnClickListener {
            openDetection()
        }
    }

    private fun setupSymptomsAdapter() {
        symptomsAdapter = SymptomsAdapter()
        binding.symptomsRecycler.adapter = symptomsAdapter
        symptomsAdapter.submitList(viewModel.getSymptoms())
    }

    private fun openDetection() {
        findNavController().navigate(R.id.detectedFragment)
    }
}