package com.beksar.testcar.domain.repository

import com.beksar.testcar.core.Result
import com.beksar.testcar.data.remoteSource.model.ProfileRemote
import com.beksar.testcar.data.remoteSource.model.UserRemote
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {

    /**
     * Получение всех пользователей
     * @param page
     */
    suspend fun users(page : Int) : Flow<Result<List<UserRemote>>>

    /**
     * Полученин профиля пользователя
     * @param userId
     */
    suspend fun profile(userId : String) : Flow<Result<ProfileRemote>>
}