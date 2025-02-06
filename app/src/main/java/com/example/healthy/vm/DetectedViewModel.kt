package com.example.healthy.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetectedViewModel : ViewModel() {

    private val _companiesFlow = MutableStateFlow<List<String>>(emptyList())
    val companiesFlow: StateFlow<List<String>> = _companiesFlow.asStateFlow()

    private val db = FirebaseFirestore.getInstance()

    fun fetchCompanies() {
        viewModelScope.launch {

            val companies = mutableListOf<String>()

            db.collection("insuranceCompanies")
                .get()
                .addOnSuccessListener { result: QuerySnapshot ->
                    for (document in result) {
                        val imageUrl = document.getString("image") ?: ""
                        companies.add(imageUrl)
                    }


                    _companiesFlow.value = companies

                }
                .addOnFailureListener { _companiesFlow.value = emptyList() }

        }
    }
}