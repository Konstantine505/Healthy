package com.example.healthy.screens

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.healthy.adapter.CompaniesAdapter
import com.example.healthy.base.BaseBottomSheetFragment
import com.example.healthy.databinding.DetectedFragmentBinding
import com.example.healthy.vm.DetectedViewModel
import kotlinx.coroutines.launch

class DetectedFragment : BaseBottomSheetFragment<DetectedFragmentBinding>(
    DetectedFragmentBinding::inflate
) {

    private val viewModel: DetectedViewModel by viewModels()

    private lateinit var companiesAdapter: CompaniesAdapter

    override fun setUp() {
        setUpAdapter()
        viewModel.fetchCompanies()
    }

    override fun bindObserves() {
        super.bindObserves()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.companiesFlow.collect {
                    companiesAdapter.submitList(it)
                }

            }
        }
    }

    override fun listeners() {
        super.listeners()
        binding.btnDone.setOnClickListener {
            dismiss()
        }
    }

    private fun setUpAdapter() {
        companiesAdapter = CompaniesAdapter()
        binding.insurancesPager.adapter = companiesAdapter
    }
}