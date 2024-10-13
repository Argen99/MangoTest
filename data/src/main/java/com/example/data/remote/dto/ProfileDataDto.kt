package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.model.ProfileData
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDataDto(
    val name: String,
    val username: String,
    val birthday: String?,
    val city: String?,
    val phone: String,
    val avatar: String?,
): DataMapper<ProfileData> {

    override fun toModel() = ProfileData(
        name = name,
        username = username,
        birthday = birthday,
        city = city,
        phone = phone,
        avatarUri = avatar,
    )
}

fun ProfileData.toDto() = ProfileDataDto(
    name = name,
    username = username,
    birthday = birthday,
    city = city,
    phone = phone,
    avatar = avatarUri
)