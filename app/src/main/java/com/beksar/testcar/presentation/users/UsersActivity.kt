package com.beksar.testcar.presentation.users

import android.content.Intent
import android.os.Bundle
import android.widget.AbsListView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beksar.testcar.R
import com.beksar.testcar.core.BaseActivity
import com.beksar.testcar.databinding.ActivityUsersBinding
import com.beksar.testcar.domain.model.User
import com.beksar.testcar.presentation.profile.ProfileActivity
import com.beksar.testcar.presentation.users.adapter.UsersAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Список всех пользователей
 */
@AndroidEntryPoint
class UsersActivity : BaseActivity() {

    private val binding by viewBinding(ActivityUsersBinding::bind)
    private val viewModel by viewModels<UsersViewModel>()
    private val adapter = UsersAdapter()

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

    private fun bindView() {
        binding.userRV.adapter = adapter
        binding.userRV.addOnScrollListener(this.scrollListener)
        adapter.openProfile = {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra(ProfileActivity.USER_ID, it)
            startActivity(intent)
        }
    }

    /**
     * Пагинация
     */

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 30
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.loadUsers()
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }


}