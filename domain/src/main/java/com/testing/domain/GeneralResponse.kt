package com.testing.domain

sealed class GeneralResponse<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T? = null) : GeneralResponse<T>(data = data)
    class Error<T>(errorMessage: String?, code: Int?) : GeneralResponse<T>(
        errorMessage = errorMessage,
        code = code
    )
}