package com.example.api.graphapi.dtos;

import com.google.gson.annotations.SerializedName;

public class FacebookPictureUrl {
  private final String url;

  @SerializedName("is_silhouette")
  private final String isSilhouette;

  public FacebookPictureUrl(String url, String isSilhouette) {
    this.url = url;
    this.isSilhouette = isSilhouette;
  }

  public String getUrl() {
    return url;
  }

  public String getIsSilhouette() {
    return isSilhouette;
  }
}
