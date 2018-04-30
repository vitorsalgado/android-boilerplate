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
}
