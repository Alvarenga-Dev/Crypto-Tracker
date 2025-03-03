package com.alvarengadev.cryptotracker.core.data.networking

import com.alvarengadev.cryptotracker.core.domain.util.NetworkError
import com.alvarengadev.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (unresolvedAddressException: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET_CONNECTION)
    } catch (serializationException: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (exception: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN_ERROR)
    }

    return responseToResult(response)
}