package com.example.api

import retrofit2.Response
import java.io.IOException

class ApiResponse<T> {
  private val statusCode: Int

  val body: T?

  private val errorMessage: String?

  val isSuccessful: Boolean
    get() = statusCode in 200..399

  constructor(error: Throwable) {
    statusCode = 500
    body = null
    errorMessage = error.message
  }

  internal constructor(response: Response<T>) {
    statusCode = response.code()

    if (response.isSuccessful) {
      body = response.body()
      errorMessage = null
    } else {
      var message: String? = null

      if (response.errorBody() != null) {
        try {
          message = response.errorBody()!!.string()
        } catch (ignored: IOException) {
        }
      }

      if (message == null || message.isEmpty()) {
        message = response.message()
      }

      errorMessage = message
      body = null
    }
  }
}
