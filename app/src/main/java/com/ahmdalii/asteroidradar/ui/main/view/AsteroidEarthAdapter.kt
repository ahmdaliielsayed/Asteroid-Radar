package com.ahmdalii.asteroidradar.ui.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmdalii.asteroidradar.databinding.RowAsteroidBinding
import com.ahmdalii.asteroidradar.model.AsteroidEarth

class AsteroidEarthAdapter(private val clickListener: AsteroidEarthListener) : ListAdapter<AsteroidEarth, RecyclerView.ViewHolder>(AsteroidEarthDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val asteroidEarthItem = getItem(position) as AsteroidEarth
                holder.bind(asteroidEarthItem, clickListener)
            }
        }
    }

    class ViewHolder private constructor(val binding: RowAsteroidBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AsteroidEarth, clickListener: AsteroidEarthListener) {
            binding.asteroidEarth = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowAsteroidBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AsteroidEarthDiffCallback : DiffUtil.ItemCallback<AsteroidEarth>() {
    override fun areItemsTheSame(oldItem: AsteroidEarth, newItem: AsteroidEarth): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AsteroidEarth, newItem: AsteroidEarth): Boolean {
        return oldItem == newItem
    }
}

class AsteroidEarthListener(val clickListener: (asteroidEarth: AsteroidEarth) -> Unit) {
    fun onClick(asteroidEarth: AsteroidEarth) = clickListener(asteroidEarth)
}
