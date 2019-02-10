package br.com.vitorsalgado.example.utils

import br.com.vitorsalgado.example.utils.FX.anyOf
import br.com.vitorsalgado.example.utils.FX.curry
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FXTest {
  @Test
  fun `anyOf should return true when any of functions evaluates to true`() {
    assertTrue {
      anyOf(::isTest, ::isA)("test")
    }
  }

  @Test
  fun `anyOf should return false if all functions evaluates to false`() {
    assertFalse {
      anyOf(::isTest, ::isA)("nothing")
    }
  }

  @Test
  fun `curry should reduce a function with multiple arguments into a function that accepts just one`() {
    val fn: (Int, Int) -> Int = { a, b -> a + b }
    val addTwo = curry(fn)(2)

    val result = addTwo(2)

    assertEquals(4, result)
  }

  private fun isTest(value: String): Boolean = value == "test"
  private fun isA(value: String): Boolean = value == "a"
}
