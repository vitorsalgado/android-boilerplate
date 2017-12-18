package br.com.vitorsalgado.androidstarter.uava.api;

import android.support.annotation.NonNull;

import java.lang.reflect.Type;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

class UavaCallAdapter<R> implements CallAdapter<R, Observable<ApiResponse<R>>> {
	private final Type responseType;

	UavaCallAdapter(Type responseType) {
		this.responseType = responseType;
	}

	@Override
	public Type responseType() {
		return responseType;
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
