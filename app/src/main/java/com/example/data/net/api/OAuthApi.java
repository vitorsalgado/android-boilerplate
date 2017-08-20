package com.example.data.net.api;

import android.support.annotation.Nullable;

import com.example.BuildConfig;
import com.example.data.net.api.dtos.TokenResponse;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public interface OAuthApi {
	@POST("oauth/token")
	@FormUrlEncoded
	Observable<TokenResponse> oauthToken(
			@Field("client_id") String client_id,
			@Field("grant_type") String grant_type,
			@Field("state") String state,
			@Nullable @Field("facebook_access_token") String facebookAccessToken,
			@Nullable @Field("access_token") String access_token,
			@Nullable @Field("refresh_token") String refresh_token);

	class Builder {
		static OAuthApi oAuthApi = null;

		public static OAuthApi get() {
			if (oAuthApi == null) {
				oAuthApi = build();
			}

			return oAuthApi;
		}

		static OAuthApi build() {
			final OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

			if (BuildConfig.DEBUG) {
				builder.addInterceptor(new HttpLoggingInterceptor().setLevel(BODY));
			}

			return new Retrofit.Builder()
					.baseUrl(BuildConfig.API_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
					.client(builder.build())
					.build()
					.create(OAuthApi.class);
		}
	}
}
