package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.model.UpdateUserDataBody

data class UpdateUserDataBodyDto(
    val phone: String,
    val name: String,
    val username: String,
    val city: String?,
    val birthday: String?,
    val avatar: Avatar64Dto?,
): DataMapper<UpdateUserDataBody> {

    override fun toModel() = UpdateUserDataBody(
        phone = phone,
        name = name,
        username = username,
        city = city,
        birthday = birthday,
        avatar = avatar?.toModel(),
    )
}

fun UpdateUserDataBody.toDto() = UpdateUserDataBodyDto(
    phone = phone,
    name = name,
    username = username,
    city = city,
    birthday = birthday,
    avatar = avatar?.toDto()
)