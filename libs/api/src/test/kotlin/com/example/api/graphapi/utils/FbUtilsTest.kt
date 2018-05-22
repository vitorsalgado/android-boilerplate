package com.example.api.graphapi.utils

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class FbUtilsTest {
  @Test
  fun convertShouldReturnParamsSeparatedByComma() {
    val params = Arrays.asList(Fields.ID, Fields.NAME, Fields.EMAIL, Fields.BIRTHDAY, Fields.PICTURE_LARGE).toTypedArray()
    val result = FbUtils.convert(params)

    assertNotNull(result)
    assertFalse(result.isEmpty())
    assertTrue("id,", result.contains("id,"))
    assertTrue("name,", result.contains("name,"))
    assertTrue(",picture", result.contains(",picture"))
  }
}
