package com.beksar.testcar.domain.model

class Profile(
    val userId: Int,
    val userName: String,
    val avatar: String,
    val name: String,
    val followers: Int,
    val following: Int,
    val email: String,
    val organizationsUrl: String,
    val createdAt: String
)

data class ProfileData(
    val title : String,
    val description : String
)