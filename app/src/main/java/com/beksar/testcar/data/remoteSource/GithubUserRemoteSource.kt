package com.beksar.testcar.data.remoteSource

import com.beksar.testcar.data.remoteSource.model.ProfileRemote
import com.beksar.testcar.data.remoteSource.model.UserRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserRemoteSource {

    @GET("users")
    suspend fun getUsers(
        @Query("since") id: Int
    ): Response<List<UserRemote>>

    @GET("users/{userName}")
    suspend fun getUserByUserName(
        @Path("userName") userName: String
    ): Response<ProfileRemote>
}