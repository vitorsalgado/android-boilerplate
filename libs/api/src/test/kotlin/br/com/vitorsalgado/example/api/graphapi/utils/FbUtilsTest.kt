package br.com.vitorsalgado.example.api.graphapi.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Arrays

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
