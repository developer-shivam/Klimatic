package app.klimatic.ui.utils

import app.klimatic.R

object ErrorUtils {
    const val ERROR_CODE_GENERIC = 100

    fun resolveErrorCode(errorCode: Int?): Int {
        return when(errorCode) {
            else -> R.string.error_code_generic
        }
    }
}