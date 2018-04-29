package com.example.api;

import android.support.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class ApiAdapterFactory extends CallAdapter.Factory {
  @Override
  public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
    if (getRawType(returnType) != Observable.class) {
      return null;
    }

    Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
    Class<?> rawObservableType = getRawType(observableType);

    if (rawObservableType != ApiResponse.class) {
      throw new IllegalArgumentException("type must be a resource");
    }

    if (!(observableType instanceof ParameterizedType)) {
      throw new IllegalArgumentException("resource must be parametrized");
    }

    Type bodyType = getParameterUpperBound(0, (ParameterizedType) observableType);

    return new ApiCallAdapter<>(bodyType);
  }
}
