package app.klimatic.data.response

import app.klimatic.ui.utils.ErrorUtils.ERROR_CODE_GENERIC

sealed class Response<T>(private val success: T?, private val error: Failure?) {

    class Success<T>(val data: T) : Response<T>(data, null)
    class Error<T>(private val error: Failure): Response<T>(null, error) {
        constructor(errorCode: Int): this(Failure(errorCode))
        constructor(errorCode: Int, errorMessage: String): this(Failure(errorCode, errorMessage))
        constructor(errorCode: Int, error: Throwable?): this(Failure(errorCode, error))
        constructor(): this(Failure(ERROR_CODE_GENERIC))
        val errorCode get() = error.errorCode
    }
}