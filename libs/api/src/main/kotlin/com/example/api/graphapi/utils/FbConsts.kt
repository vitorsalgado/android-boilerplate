package com.example.api.graphapi.utils

import java.util.*

interface FbConsts {
  companion object {
    val FACEBOOK_USER_FIELDS: MutableList<String> = Collections.unmodifiableList(
      Arrays.asList(
        Fields.ID,
        Fields.NAME,
        Fields.FIRST_NAME,
        Fields.EMAIL,
        Fields.GENDER,
        Fields.BIRTHDAY,
        Fields.PICTURE_LARGE,
        Fields.HOMETOWN,
        Fields.ALBUMS
      )
    )
  }
}
