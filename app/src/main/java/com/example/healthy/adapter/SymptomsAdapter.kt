package com.example.healthy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.healthy.databinding.SymptomItemViewBinding

class SymptomsAdapter() :
    ListAdapter<String, SymptomsAdapter.SymptomsViewHolder>(SymptomsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SymptomsViewHolder(
        SymptomItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: SymptomsViewHolder, position: Int) {
        holder.bind()
    }

    inner class SymptomsViewHolder(private val binding: SymptomItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: String

        fun bind() {
            model = currentList[adapterPosition]
            binding.apply {
                tvSymtom.text = model
                root.setOnClickListener {
                    if (!cbSelect.isChecked) {
                        cbSelect.isChecked = true
                    }else{
                        cbSelect.isChecked = false
                    }
                }
            }
        }
    }

    class SymptomsDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
