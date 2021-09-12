package com.beksar.testcar.data.repository

import com.beksar.testcar.core.Result
import com.beksar.testcar.core.toDomainFlow
import com.beksar.testcar.data.remoteSource.GithubUserRemoteSource
import com.beksar.testcar.data.remoteSource.model.ProfileRemote
import com.beksar.testcar.data.remoteSource.model.UserRemote
import com.beksar.testcar.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(private val service: GithubUserRemoteSource) :
    GithubUserRepository {

    override suspend fun users(page: Int): Flow<Result<List<UserRemote>>> =
        service.getUsers(page).toDomainFlow()

    override suspend fun profile(userId: String): Flow<Result<ProfileRemote>> =
        service.getUserByUserName(userId).toDomainFlow()
}