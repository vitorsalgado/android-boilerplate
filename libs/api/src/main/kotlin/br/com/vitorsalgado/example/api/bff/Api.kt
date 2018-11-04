package br.com.vitorsalgado.example.api.bff

import br.com.vitorsalgado.example.api.ApiResponse
import br.com.vitorsalgado.example.api.bff.dtos.OAuthResponse

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
  @POST("oauth/token")
  @FormUrlEncoded
  fun getToken(
    @Field("client_id") client_id: String,
    @Field("state") state: String,
    @Field("grant_type") grant_type: String,
    @Field("username") username: String,
    @Field("password") password: String
  ): Observable<ApiResponse<OAuthResponse>>

  @POST("oauth/token?grant_type=refresh_token")
  @FormUrlEncoded
  fun refreshToken(
    @Field("client_id") client_id: String,
    @Field("state") state: String,
    @Field("access_token") access_token: String?,
    @Field("refresh_token") refresh_token: String?
  ): Observable<ApiResponse<OAuthResponse>>
}
