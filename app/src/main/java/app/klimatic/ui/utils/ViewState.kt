package app.klimatic.ui.utils

import app.klimatic.ui.utils.ErrorUtils.ERROR_CODE_GENERIC

sealed class ViewState<ResultType> {

    data class Success<ResultType>(
        val data: ResultType
    ) : ViewState<ResultType>()

    data class Error<ResultType>(
        val code: Int? = ERROR_CODE_GENERIC
    ) : ViewState<ResultType>()

    class StartLoading<ResultType> : ViewState<ResultType>()

    class HideLoading<ResultType> : ViewState<ResultType>()
}
