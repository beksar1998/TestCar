package com.beksar.testcar.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

abstract class UseCase<T, Params> {

    protected abstract suspend fun buildUseCase(params: Params): Flow<Result<T>>

    suspend fun execute(params: Params, onSuccess: (T) -> Unit, onError: (String) -> Unit = {}) {
        return buildUseCase(params)
            .handleErrors {
                onError(it)
            }
            .collect {
                when (it) {
                    is Result.Success -> onSuccess(it.data)
                    is Result.Error -> onError(it.message)
                }
            }
    }

}