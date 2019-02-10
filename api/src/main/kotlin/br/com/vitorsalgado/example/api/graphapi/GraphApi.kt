package br.com.vitorsalgado.example.api.graphapi

import br.com.vitorsalgado.example.api.graphapi.dtos.FacebookAlbumPhotos
import br.com.vitorsalgado.example.api.graphapi.dtos.FacebookPhoto
import br.com.vitorsalgado.example.api.graphapi.dtos.FacebookUser

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GraphApi {
  @GET("me?locale=en_US")
  fun me(@Query("access_token") access_token: String, @Query("fields") fields: String): Single<FacebookUser>

  @GET("{albumId}/photos?locale=en_US")
  fun albumPhotos(
    @Path("albumId") albumId: String,
    @Query("access_token") access_token: String,
    @Query("fields") fields: String,
    @Query("limit") limit: Int
  ): Observable<FacebookAlbumPhotos>

  @GET("{photoId}?locale=en_US")
  fun photo(
    @Path("photoId") photoId: String,
    @Query("access_token") access_token: String,
    @Query("fields") fields: String
  ): Single<FacebookPhoto>
}
