package br.com.vitorsalgado.androidstarter.graphapi;

import br.com.vitorsalgado.androidstarter.graphapi.dtos.FacebookAlbumPhotos;
import br.com.vitorsalgado.androidstarter.graphapi.dtos.FacebookPhoto;
import br.com.vitorsalgado.androidstarter.graphapi.dtos.FacebookUser;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GraphApi {
	@GET("me?locale=en_US")
	Single<FacebookUser> me(@Query("access_token") String access_token, @Query("fields") String fields);

	@GET("{albumId}/photos?locale=en_US")
	Observable<FacebookAlbumPhotos> albumPhotos(@Path("albumId") String albumId, @Query("access_token") String access_token, @Query("fields") String fields, @Query("limit") int limit);

	@GET("{photoId}?locale=en_US")
	Single<FacebookPhoto> photo(@Path("photoId") String photoId, @Query("access_token") String access_token, @Query("fields") String fields);
}
