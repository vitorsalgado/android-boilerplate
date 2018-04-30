package com.example.api.graphapi.dtos

import com.google.gson.annotations.SerializedName

class FacebookPictureUrl(val url: String, @field:SerializedName("is_silhouette")
val isSilhouette: String)
