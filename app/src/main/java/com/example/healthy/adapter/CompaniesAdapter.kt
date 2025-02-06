package com.example.healthy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthy.databinding.InsuranceCompaniesPagerItemBinding
import com.example.healthy.loadImage

class CompaniesAdapter() :
    ListAdapter<String, CompaniesAdapter.CompaniesViewHolder>(CompaniesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CompaniesViewHolder(
        InsuranceCompaniesPagerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: CompaniesViewHolder, position: Int) {
        holder.bind()
    }

    inner class CompaniesViewHolder(private val binding: InsuranceCompaniesPagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: String

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                ivSource.loadImage(model)

            }
        }
    }

    class CompaniesDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
