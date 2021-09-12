package com.beksar.testcar.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response

fun <T : Any> Response<T>.toDomainFlow(): Flow<Result<T>> {
    return flow {
        val apiCall = this@toDomainFlow
        if (apiCall.isSuccessful && apiCall.body() != null) {
            emit(Result.Success(apiCall.body()!!))
        } else {
            emit(Result.Error(apiCall.message()))
        }
    }
}


fun <T> Flow<T>.handleErrors(onError: (String) -> Unit = {}): Flow<T> = flow {
    try {
        collect { value -> emit(value) }
    } catch (e: Throwable) {
        onError(e.message ?: "")
    }
}