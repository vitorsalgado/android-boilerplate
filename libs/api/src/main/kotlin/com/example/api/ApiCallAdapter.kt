package com.example.api

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

internal class ApiCallAdapter<R>(private val typeOfResponse: Type) : CallAdapter<R, Observable<ApiResponse<R>>> {
  override fun responseType(): Type {
    return typeOfResponse
  }

  override fun adapt(call: Call<R>): Observable<ApiResponse<R>> {
    return Observable.create { emitter ->
      call.enqueue(object : Callback<R> {
        override fun onResponse(call: Call<R>, response: Response<R>) {
          emitter.onNext(ApiResponse(response))
        }

        override fun onFailure(call: Call<R>, throwable: Throwable) {
          emitter.onError(throwable)
        }
      })
    }
  }
}
