package br.com.vitorsalgado.example.toolkit

object Preconditions {
  fun <T> checkNotNull(reference: T?): T {
    if (reference == null) {
      throw NullPointerException()
    }

    return reference
  }

  fun <T> checkNotNull(reference: T?, errorMessage: Any?): T {
    if (reference == null) {
      throw NullPointerException(errorMessage.toString())
    }

    return reference
  }

  fun checkNotNullOrEmpty(value: String?, errorMessage: String?): String {
    if (value == null || value.isEmpty()) {
      throw IllegalArgumentException(errorMessage)
    }

    return value
  }

  fun checkArgument(expression: Boolean, errorMessage: Any?) {
    if (!expression) {
      throw IllegalArgumentException(errorMessage.toString())
    }
  }
}
