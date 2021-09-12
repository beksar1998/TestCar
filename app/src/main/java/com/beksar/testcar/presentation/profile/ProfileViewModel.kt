package com.beksar.testcar.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beksar.testcar.core.BaseViewModel
import com.beksar.testcar.core.Status
import com.beksar.testcar.domain.model.Profile
import com.beksar.testcar.domain.useCase.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: GetProfileUseCase) :
    BaseViewModel() {

    val profileLiveData = MutableLiveData<MutableList<Profile>>()

    fun loadProfile(userName: String) {
        viewModelScope.launch {
            setLoadingStatus(Status.SHOW_LOADING)
            profileUseCase.execute(userName, {
                profileLiveData.value = it.toMutableList()
            }, {
                setLoadingStatus(Status.ERROR)
            })
            setLoadingStatus(Status.HIDE_LOADING)
        }

    }
}