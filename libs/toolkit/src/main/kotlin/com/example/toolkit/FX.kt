package com.example.toolkit

object FX {
  fun <T> anyOf(vararg fns: (T) -> Boolean): (T) -> Boolean {
    return fun(source: T): Boolean {
      fns.forEach { f ->
        if (f(source)) {
          return true
        }
      }

      return false
    }
  }

  fun <T> allOf(vararg fns: (T) -> Boolean): (T) -> Boolean {
    return fun(source: T): Boolean {
      fns.forEach { f ->
        if (!f(source)) {
          return false
        }
      }

      return true
    }
  }

  fun <A1, A2, R> curry(fn: (A1, A2) -> R): (A1) -> (A2) -> R {
    return fun(a: A1): (A2) -> R {
      return fun(b: A2): R {
        return fn(a, b)
      }
    }
  }
}
