package com.beksar.testcar.presentation.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beksar.testcar.databinding.ItemProfileBinding
import com.beksar.testcar.domain.model.Profile


class ProfileAdapter : ListAdapter<Profile, ProfileAdapter.ProfileViewHolder>(diffCallback) {

    inner class ProfileViewHolder(private val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val profile = getItem(position)
            binding.apply {
                textViewTitle.text = profile.title
                textViewDescription.text = profile.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(position)
    }

}


private val diffCallback =
    object : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(
            oldItem: Profile,
            newItem: Profile
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: Profile,
            newItem: Profile
        ): Boolean {
            return oldItem == newItem
        }

    }