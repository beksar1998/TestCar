package com.beksar.testcar.presentation.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.beksar.testcar.databinding.ItemUsersBinding
import com.beksar.testcar.domain.model.User


class UsersAdapter : ListAdapter<User, UsersAdapter.UserViewHolder>(diffCallback) {

    var openProfile: ((profileId: String) -> Unit)? = null

    inner class UserViewHolder(private val binding: ItemUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val user = getItem(position)
            binding.apply {
                userId.text = user.userId.toString()
                userName.text = user.userName
                avatar.load(user.avatar)
                root.setOnClickListener {
                    openProfile?.invoke(user.userName)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
    }

}


private val diffCallback =
    object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem == newItem
        }

    }