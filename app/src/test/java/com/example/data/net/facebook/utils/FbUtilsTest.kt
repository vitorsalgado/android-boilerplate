package com.example.data.net.facebook.utils

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FbUtilsTest {
    @Test
    @Throws(Exception::class)
    fun convertShouldReturnParamsSeparatedByComma() {
        val params = arrayOf(Fields.ID, Fields.NAME, Fields.EMAIL, Fields.BIRTHDAY, Fields.PICTURE_LARGE)

        val result = FbUtils.convert(params)

        assertNotNull(result)
        assertFalse(result.isEmpty())
        assertTrue("id, ", result.contains("id, "))
        assertTrue(" name, ", result.contains(" name, "))
        assertTrue(", picture", result.contains(", picture"))
    }
}
