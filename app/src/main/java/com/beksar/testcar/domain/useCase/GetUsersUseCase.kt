package com.beksar.testcar.domain.useCase

import com.beksar.testcar.core.Result
import com.beksar.testcar.core.UseCase
import com.beksar.testcar.core.mapData
import com.beksar.testcar.domain.model.User
import com.beksar.testcar.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repo: GithubUserRepository
) : UseCase<List<User>, Int>() {

    override suspend fun buildUseCase(params: Int): Flow<Result<List<User>>> {
        return repo.users(params).map {
            it.mapData {
                it.map {
                    User(
                        userId = it.userId ?: -1,
                        userName = it.userName ?: "",
                        avatar = it.avatar ?: ""
                    )
                }
            }
        }
    }
}