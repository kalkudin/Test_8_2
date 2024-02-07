package com.example.test_8.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.test_8.databinding.ItemHomeLayoutBinding
import com.example.test_8.presentation.model.HomeImage

class HomeImageViewPagerAdapter : ListAdapter<HomeImage, HomeImageViewPagerAdapter.HomeImageViewHolder>(DiffCallback()) {

    class HomeImageViewHolder(private val binding: ItemHomeLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(homeImage: HomeImage) {
            with(binding) {
                Glide.with(backgroundImage.context)
                    .load(homeImage.cover)
                    .transform(CenterCrop(), RoundedCorners(20))
                    .into(backgroundImage)

                tvLocation.text = homeImage.location
                tvReaction.text = "${homeImage.reactionCount}"
                tvDestination.text = homeImage.title
                tvPrice.text = homeImage.price
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<HomeImage>() {
        override fun areItemsTheSame(oldItem: HomeImage, newItem: HomeImage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomeImage, newItem: HomeImage): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeImageViewHolder {
        val binding = ItemHomeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeImageViewHolder, position: Int) {
        val homeImage = getItem(position)
        holder.bind(homeImage)
    }
}