package com.example.healthy.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthy.adapter.model.CompanyModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InsuranceCompaniesViewModel : ViewModel() {

    private val _companiesFlow = MutableStateFlow<List<CompanyModel>>(emptyList())
    val companiesFlow: StateFlow<List<CompanyModel>> = _companiesFlow.asStateFlow()

    private val db = FirebaseFirestore.getInstance()

    fun fetchCompanies() {
        viewModelScope.launch {

            val companies = mutableListOf<CompanyModel>()

            db.collection("insuranceCompanies")
                .get()
                .addOnSuccessListener { result: QuerySnapshot ->
                    for (document in result) {
                        val imageUrl = document.getString("image") ?: ""
                        val companyName = document.getString("name") ?: ""
                        companies.add(
                            CompanyModel(
                                name = companyName,
                                image = imageUrl
                            )
                        )
                    }


                    _companiesFlow.value = companies

                }
                .addOnFailureListener { _companiesFlow.value = emptyList() }

        }
    }
}