package com.example.api;

import android.support.annotation.NonNull;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

class ApiCallAdapter<R> implements CallAdapter<R, Observable<ApiResponse<R>>> {
  private final Type typeOfResponse;

  ApiCallAdapter(Type typeOfResponse) {
    this.typeOfResponse = typeOfResponse;
  }

  @Override
  public Type responseType() {
    return typeOfResponse;
  }

  @Override
  public Observable<ApiResponse<R>> adapt(@NonNull Call<R> call) {
    return Observable.create(emitter ->
      call.enqueue(new Callback<R>() {
        @Override
        public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
          emitter.onNext(new ApiResponse<>(response));
        }

        @Override
        public void onFailure(@NonNull Call<R> call, @NonNull Throwable throwable) {
          emitter.onError(throwable);
        }
      })
    );
  }
}
