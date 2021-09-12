package com.beksar.testcar.domain.useCase

import com.beksar.testcar.core.Result
import com.beksar.testcar.core.UseCase
import com.beksar.testcar.core.mapData
import com.beksar.testcar.domain.model.Profile
import com.beksar.testcar.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repo: GithubUserRepository
) : UseCase<List<Profile>, String>() {
    override suspend fun buildUseCase(params: String): Flow<Result<List<Profile>>> {
        return repo.profile(params).map {
            it.mapData {

                val profileData = mutableListOf<Profile>()
                profileData.add(
                    Profile(
                        title = "User id",
                        description = it.userId.toString()
                    )
                )

                profileData.add(
                    Profile(
                        title = "User name",
                        description = it.userName ?: ""
                    )
                )

                profileData.add(
                    Profile(
                        title = "Name",
                        description = it.name ?: ""
                    )
                )

                profileData.add(
                    Profile(
                        title = "Followers",
                        description = it.followers.toString()
                    )
                )

                profileData.add(
                    Profile(
                        title = "Following",
                        description = it.following.toString()
                    )
                )

                profileData.add(
                    Profile(
                        title = "Email",
                        description = it.email ?: ""
                    )
                )


                profileData.add(
                    Profile(
                        title = "Organization",
                        description = it.organizationsUrl ?: ""
                    )
                )


                profileData.add(
                    Profile(
                        title = "",
                        description = it.createdAt ?: ""
                    )
                )
                return@mapData profileData
            }
        }
    }
}
