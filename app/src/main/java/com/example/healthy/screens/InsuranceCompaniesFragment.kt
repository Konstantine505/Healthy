package com.example.healthy.screens

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.healthy.adapter.CompaniesRecyclerAdapter
import com.example.healthy.base.BaseFragment
import com.example.healthy.databinding.InsuranceCompaniesFragmentBinding
import com.example.healthy.vm.InsuranceCompaniesViewModel
import kotlinx.coroutines.launch

class InsuranceCompaniesFragment : BaseFragment<InsuranceCompaniesFragmentBinding>(
    InsuranceCompaniesFragmentBinding::inflate
) {

    private val viewModel: InsuranceCompaniesViewModel by viewModels()

    private lateinit var companiesAdapter: CompaniesRecyclerAdapter

    override fun setUp() {
        setUpRecycler()
        bindObserves()
        viewModel.fetchCompanies()
    }

    override fun listeners() {
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

    private fun setUpRecycler() {
        companiesAdapter = CompaniesRecyclerAdapter()
        binding.companiesRecycler.adapter = companiesAdapter
    }
}
