package br.com.vitorsalgado.example.api.graphapi.dtos

import com.google.gson.annotations.SerializedName

class FacebookUser {
  val id: String? = null

  val name: String? = null

  @SerializedName("first_name")
  val firstName: String? = null

  @SerializedName("last_name")
  val lastName: String? = null

  val link: String? = null

  val username: String? = null

  val gender: String? = null

  val email: String? = null

  val birthday: String? = null

  val bio: String? = null

  val picture: FacebookPicture? = null

  val albums: FacebookAlbum? = null
}
