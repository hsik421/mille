package com.project.millie

import retrofit2.HttpException

suspend fun <T> callApi(call: suspend () -> T): ApiResult<T> {
    return try {
        val response = call()
        if (response == null)
            ApiResult.Failure(Exception("Response is null"))
        else
            ApiResult.Success(response)
    } catch (e: HttpException) {
        ApiResult.Failure(Exception("http - code : ${e.code()}, message = ${e.response()?.message()}"))
    } catch (e: Exception) {
        ApiResult.Failure(Exception("message : ${e.message}"))
    }
}