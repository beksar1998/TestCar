package com.beksar.testcar.domain.useCase

import com.beksar.testcar.core.Result
import com.beksar.testcar.core.UseCase
import com.beksar.testcar.core.mapData
import com.beksar.testcar.domain.model.ProfileData
import com.beksar.testcar.domain.repository.GithubUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val repo: GithubUserRepository
) : UseCase<List<ProfileData>, String>() {
    override suspend fun buildUseCase(params: String): Flow<Result<List<ProfileData>>> {
        return repo.profile(params).map {
            it.mapData {

                val profileData = mutableListOf<ProfileData>()
                profileData.add(
                    ProfileData(
                        title = "User id",
                        description = it.userId.toString()
                    )
                )

                profileData.add(
                    ProfileData(
                        title = "User name",
                        description = it.userName ?: ""
                    )
                )

                profileData.add(
                    ProfileData(
                        title = "Name",
                        description = it.name ?: ""
                    )
                )

                profileData.add(
                    ProfileData(
                        title = "Followers",
                        description = it.followers.toString()
                    )
                )

                profileData.add(
                    ProfileData(
                        title = "Following",
                        description = it.following.toString()
                    )
                )

                profileData.add(
                    ProfileData(
                        title = "Email",
                        description = it.email ?: ""
                    )
                )


                profileData.add(
                    ProfileData(
                        title = "Organization",
                        description = it.organizationsUrl ?: ""
                    )
                )


                profileData.add(
                    ProfileData(
                        title = "",
                        description = it.createdAt ?: ""
                    )
                )
                return@mapData profileData
            }
        }
    }
}
