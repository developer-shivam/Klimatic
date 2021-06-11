package app.klimatic.ui.utils

import app.klimatic.data.response.Response

suspend fun <T> getResponse(
    func: suspend () -> T
): Response<T> = try {
    Response.Success(data = func())
} catch (e: Exception) {
    Response.Error()
}

fun <T> Response<T>.toDataOrNull(): T? {
    return when (this) {
        is Response.Success -> data
        else -> null
    }
}

fun <T> Response<T>.toErrorCode(): Int? {
    return when (this) {
        is Response.Error -> errorCode
        else -> null
    }
}
