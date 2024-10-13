package com.example.data.remote.dto

import com.example.data.utils.DataMapper
import com.example.domain.model.Avatar64
import kotlinx.serialization.SerialName

data class Avatar64Dto(
    val filename: String,
    val base_64: String,
): DataMapper<Avatar64> {

    override fun toModel() = Avatar64(
        filename = filename,
        base64 = base_64,
    )
}

fun Avatar64.toDto() = Avatar64Dto(
    filename = filename,
    base_64 = base64,
)