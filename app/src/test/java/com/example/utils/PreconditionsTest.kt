package com.example.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PreconditionsTest {

    @Test
    fun shouldThrowExceptionAndReturnProvidedMessageWhenValueIsNull() {
        try {
            Preconditions.checkNotNull(null, "the message")
        } catch (ex: NullPointerException) {
            assertEquals(ex.message, "the message")
        }
    }

    @Test
    fun shouldReturnSameValueWhenNotNull() {
        var data1 = "test1"
        data1 = Preconditions.checkNotNull(data1)

        var data2 = "test2"
        data2 = Preconditions.checkNotNull(data2, "the message")

        assertEquals(data1, "test1")
        assertEquals(data2, "test2")
    }

    @Test
    fun shouldThrowExceptionWhenArgumentDoesNotSatisfyExpression() {
        try {
            Preconditions.checkArgument(false, "error message")
        } catch (ex: IllegalArgumentException) {
            assertEquals(ex.message, "error message")
        }
    }

    @Test
    fun shouldNotThrowExceptionWhenArgumentSatisfyExpression() {
        Preconditions.checkArgument(true, "message")
    }

}