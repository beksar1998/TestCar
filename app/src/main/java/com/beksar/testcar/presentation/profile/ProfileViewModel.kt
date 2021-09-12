package com.beksar.testcar.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beksar.testcar.core.Status
import com.beksar.testcar.domain.model.Profile
import com.beksar.testcar.domain.model.ProfileData
import com.beksar.testcar.domain.model.User
import com.beksar.testcar.domain.useCase.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: GetProfileUseCase) :
    ViewModel() {

    val profileLiveData = MutableLiveData<MutableList<ProfileData>>()
    val statusLiveData = MutableLiveData<Status>()

    fun loadProfile(userName: String) {
        viewModelScope.launch {
            statusLiveData.value = Status.SHOW_LOADING
            profileUseCase.execute(userName, {
                profileLiveData.value = it.toMutableList()
            }, {
                statusLiveData.value = Status.ERROR
            })
            statusLiveData.value = Status.HIDE_LOADING
        }

    }
}