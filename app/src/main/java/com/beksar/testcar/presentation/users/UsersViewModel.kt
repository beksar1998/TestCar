package com.beksar.testcar.presentation.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beksar.testcar.core.Status
import com.beksar.testcar.domain.model.User
import com.beksar.testcar.domain.useCase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUseCase: GetUsersUseCase
) : ViewModel() {

    val usersLiveData = MutableLiveData<MutableList<User>>()
    val statusLiveData = MutableLiveData<Status>()

    fun loadUsers(page: Int = 1) {
        viewModelScope.launch {
            statusLiveData.value = Status.SHOW_LOADING
            usersUseCase.execute(page, {
                usersLiveData.value = it.toMutableList()
            }, {
                statusLiveData.value = Status.ERROR
            })
            statusLiveData.value = Status.HIDE_LOADING
        }
    }


}