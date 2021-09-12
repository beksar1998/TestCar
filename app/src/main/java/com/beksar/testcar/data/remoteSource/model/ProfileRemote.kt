package com.beksar.testcar.data.remoteSource.model

import com.google.gson.annotations.SerializedName

class ProfileRemote(
    @SerializedName("id")
    val userId: Int?,
    @SerializedName("login")
    val userName: String?,
    @SerializedName("avatar_url")
    val avatar: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("following")
    val following: Int?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("organizations_url")
    val organizationsUrl: String?,
    @SerializedName("created_at")
    val createdAt: String?
)