package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.model.CheckAuthCodeBody

data class CheckAuthCodeBodyDto(
    val phone: String,
    val code: String
): DataMapper<CheckAuthCodeBody> {

    override fun toModel() = CheckAuthCodeBody(
        phone = phone,
        code = code
    )
}

fun CheckAuthCodeBody.toDto() = CheckAuthCodeBodyDto(
    phone = phone,
    code = code
)