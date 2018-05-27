package com.example.toolkit

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PreconditionsTest {
  @Test
  fun shouldThrowExceptionAndReturnProvidedMessageWhenValueIsNull() {
    try {
      Preconditions.checkNotNull<Any>(null, "the message")
    } catch (ex: NullPointerException) {
      Assert.assertEquals(ex.message, "the message")
    }
  }

  @Test
  fun shouldReturnSameValueWhenNotNull() {
    var data1 = "test1"
    data1 = Preconditions.checkNotNull(data1)

    var data2 = "test2"
    data2 = Preconditions.checkNotNull(data2, "the message")

    Assert.assertEquals(data1, "test1")
    Assert.assertEquals(data2, "test2")
  }

  @Test
  fun shouldThrowExceptionWhenArgumentDoesNotSatisfyExpression() {
    try {
      Preconditions.checkArgument(false, "error message")
    } catch (ex: IllegalArgumentException) {
      Assert.assertEquals(ex.message, "error message")
    }
  }

  @Test
  fun shouldNotThrowExceptionWhenArgumentSatisfyExpression() {
    Preconditions.checkArgument(true, "message")
  }
}
