package com.beksar.testcar.data.remoteSource.model

import com.google.gson.annotations.SerializedName

class UserRemote(
    @SerializedName("id")
    val userId: Int?,
    @SerializedName("login")
    val userName : String?,
    @SerializedName("avatar_url")
    val avatar : String?)