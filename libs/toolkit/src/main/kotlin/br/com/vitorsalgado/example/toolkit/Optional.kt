package br.com.vitorsalgado.example.toolkit

class Optional<T> private constructor(private val value: T?) {
  val isPresent: Boolean
    get() = value != null

  val isNotPresent: Boolean
    get() = value == null

  fun get(): T? {
    return value
  }

  fun getOrDefault(defaultValue: T): T? {
    return if (isPresent) {
      value
    } else defaultValue
  }

  companion object {
    fun <T> from(value: T?): Optional<T> {
      if (value == null) {
        throw NullPointerException()
      }

      return Optional(value)
    }

    fun <T> fromNullable(value: T?): Optional<T> {
      return Optional(value)
    }

    fun <T> empty(): Optional<T> {
      return Optional(null)
    }
  }
}
