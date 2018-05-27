package br.com.vitorsalgado.example.toolkit

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class OptionalTest {
  @Test
  fun `"isPresent" should return false when null source is provided`() {
    val maybe = Optional.fromNullable<Any>(null)

    assertThat(maybe.isPresent, `is`(false))
    assertThat(maybe.isNotPresent, `is`(true))
  }

  @Test
  fun `"isPresent" should return true when source is not null`() {
    val maybe = Optional.from("test data")

    assertThat(maybe.isPresent, `is`(true))
    assertThat(maybe.isNotPresent, `is`(false))
  }

  @Test(expected = NullPointerException::class)
  fun `it should throw exception when calling "from" with null source`() {
    Optional.from<Any>(null)
  }

  @Test
  fun `"empty" should return instance without data`() {
    val maybe = Optional.empty<Any>()

    assertThat(maybe.isPresent, `is`(false))
    assertThat(maybe.isNotPresent, `is`(true))
  }

  @Test
  fun `"get" should return source data`() {
    val maybe = Optional.from("test")
    val data = maybe.get()

    assertThat<String>(data, `is`("test"))
  }

  @Test
  fun `"getOrDefault" should return the default value when source is empty`() {
    val maybe = Optional.fromNullable<String>(null)
    val data = maybe.getOrDefault("test")

    assertThat<String>(data, `is`("test"))
  }

  @Test
  fun `"getOrDefault" should return source data when optional is not empty`() {
    val maybe = Optional.fromNullable("test")
    val data = maybe.getOrDefault("test 2")

    assertThat<String>(data, `is`("test"))
  }
}
