package app.klimatic.data.response

data class Failure(val errorCode: Int, val error: Throwable? = null) {
    constructor(code: Int, message: String): this(code, Throwable(message))
}