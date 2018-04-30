package com.example.toolkit

import com.example.toolkit.FX.anyOf
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class FXTest {
  @Test
  fun `it should return true when any of functions evaluates to true`() {
    assertTrue {
      anyOf(::isTest, ::isA)("test")
    }
  }

  @Test
  fun `it should return false if all functions evaluates to false`() {
    assertFalse {
      anyOf(::isTest, ::isA)("nothing")
    }
  }

  private fun isTest(value: String): Boolean = value == "test"
  private fun isA(value: String): Boolean = value == "a"
}
