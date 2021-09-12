package com.beksar.testcar.presentation.users

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beksar.testcar.R
import com.beksar.testcar.core.ProgressBar
import com.beksar.testcar.core.Status
import com.beksar.testcar.core.hide
import com.beksar.testcar.core.show
import com.beksar.testcar.databinding.ActivityUsersBinding
import com.beksar.testcar.domain.model.User
import com.beksar.testcar.presentation.profile.ProfileActivity
import com.beksar.testcar.presentation.users.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Список всех пользователей
 */
@AndroidEntryPoint
class UsersActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityUsersBinding::bind)
    private val viewModel by viewModels<UsersViewModel>()
    private val adapter = UsersAdapter()
    private val progressbar = ProgressBar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        bindView()
        bindViewModels()
    }

    private fun bindViewModels() {
        viewModel.usersLiveData.observe(this, usersObserver)
        viewModel.statusLiveData.observe(this, statusObserver)
        viewModel.loadUsers()
    }

    private val usersObserver = Observer<MutableList<User>> {
        adapter.submitList(it)
    }

    private val statusObserver = Observer<Status> {
        when (it) {
            Status.SHOW_LOADING -> {
                progressbar.show(supportFragmentManager)
            }
            Status.HIDE_LOADING -> {
                progressbar.hide()
            }
            Status.ERROR -> {
                progressbar.hide()
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun bindView() {
        binding.userRV.adapter = adapter
        adapter.openProfile = {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(ProfileActivity.USER_ID, it)
            startActivity(intent)
        }
    }

}