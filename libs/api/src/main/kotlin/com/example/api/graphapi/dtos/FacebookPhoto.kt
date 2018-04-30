package com.example.api.graphapi.dtos

class FacebookPhoto(val id: String, val images: List<FacebookImage>) {

  val largest: FacebookImage
    get() = images[0]

  val smallest: FacebookImage
    get() = images[images.size - 1]
}
