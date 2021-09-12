package com.beksar.testcar.presentation.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beksar.testcar.core.BaseViewModel
import com.beksar.testcar.core.Status
import com.beksar.testcar.domain.model.User
import com.beksar.testcar.domain.useCase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUseCase: GetUsersUseCase
) : BaseViewModel() {
    var userId = 0
    val usersLiveData = MutableLiveData<MutableList<User>>()

    fun loadUsers() {
        viewModelScope.launch {
            setLoadingStatus(Status.SHOW_LOADING)
            usersUseCase.execute(userId, {
                val list = mutableListOf<User>()
                usersLiveData.value?.let { it1 -> list.addAll(it1) }
                list.addAll(it)
                usersLiveData.value = list
            }, {
                setLoadingStatus(Status.ERROR)
            })
            setLoadingStatus(Status.HIDE_LOADING)
            userId += 30
        }
    }


}