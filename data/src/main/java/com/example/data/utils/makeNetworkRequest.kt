package com.example.data.utils

import com.example.common.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

internal fun <T> makeNetworkRequest(
    request: suspend () -> T
) =
    flow<Either<String, T>> {
        request().also {
            emit(Either.Success(value = it))
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        exception.printStackTrace()
        emit(Either.Error(value = exception.message ?: "Unknown error"))
    }