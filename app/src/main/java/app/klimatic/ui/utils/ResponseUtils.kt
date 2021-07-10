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

fun <T> handleState(
    state: ViewState<T>,
    success: (data: T) -> Unit,
    error: (errorCode: Int?) -> Unit
) {
    when (state) {
        is ViewState.Success -> success(state.data)
        is ViewState.Error -> error(state.code)
    }
}

fun <T> handleState(
    state: ViewState<T>,
    success: (data: T) -> Unit,
    error: (errorCode: Int?) -> Unit,
    showLoading: () -> Unit,
    hideLoading: () -> Unit
) {
    when (state) {
        is ViewState.Success -> success(state.data)
        is ViewState.Error -> error(state.code)
        is ViewState.ShowLoading -> showLoading()
        is ViewState.HideLoading -> hideLoading()
    }
}
