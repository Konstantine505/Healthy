package com.example.healthy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthy.adapter.model.CompanyModel
import com.example.healthy.databinding.InsuranceCompanyItemViewBinding
import com.example.healthy.loadImage

class CompaniesRecyclerAdapter() :
    ListAdapter<CompanyModel, CompaniesRecyclerAdapter.CompaniesViewHolder>(CompaniesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CompaniesViewHolder(
        InsuranceCompanyItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CompaniesViewHolder, position: Int) {
        holder.bind()
    }

    inner class CompaniesViewHolder(private val binding: InsuranceCompanyItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: CompanyModel

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                ivSource.loadImage(model.image)
                tvCompanyName.text = model.name

            }
        }
    }

    class CompaniesDiffUtil : DiffUtil.ItemCallback<CompanyModel>() {
        override fun areItemsTheSame(oldItem: CompanyModel, newItem: CompanyModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CompanyModel, newItem: CompanyModel): Boolean {
            return oldItem == newItem
        }
    }
}
