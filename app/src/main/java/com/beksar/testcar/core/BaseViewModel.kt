package com.beksar.testcar.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    /**
     * LiveData для проверки состояние загрузки
     */
    val statusLiveData = MutableLiveData<Status>()

    /**
     * Метод для установки значения загрузки
     */
    fun setLoadingStatus(status: Status) {
        statusLiveData.value = status
    }
}