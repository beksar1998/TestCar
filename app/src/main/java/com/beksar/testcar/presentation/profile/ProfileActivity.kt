package com.beksar.testcar.presentation.profile

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.beksar.testcar.R
import com.beksar.testcar.core.BaseActivity
import com.beksar.testcar.databinding.ActivityProfileBinding
import com.beksar.testcar.domain.model.Profile
import com.beksar.testcar.presentation.profile.adapter.ProfileAdapter
import dagger.hilt.android.AndroidEntryPoint


/**
 * Профиль пользователя
 */
@AndroidEntryPoint
class ProfileActivity : BaseActivity() {

    private val binding by viewBinding(ActivityProfileBinding::bind)
    private val viewModel by viewModels<ProfileViewModel>()
    private val adapter = ProfileAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        bindViews()
        bindViewModels()

    }

    private fun bindViewModels() {
        viewModel.loadProfile(intent.extras?.getString(USER_ID) ?: "")
        viewModel.profileLiveData.observe(this, profileObserver)
        viewModel.statusLiveData.observe(this, statusObserver)
    }

    private fun bindViews() {
        binding.profileRV.adapter = adapter
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private val profileObserver = Observer<List<Profile>> {
        adapter.submitList(it)
    }




    /**
     * переопределение кнопки назад в toolbar
     * если есть меню нужно обработать через кнопку android.R.id.home
     */
    /**
     * переопределение кнопки назад в toolbar
     * если есть меню нужно обработать через кнопку android.R.id.home
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            finish()
            return true
        }
        return false
    }


    companion object {
        const val USER_ID = "USER_ID"
    }
}