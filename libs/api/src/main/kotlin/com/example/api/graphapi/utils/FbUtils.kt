package com.example.api.graphapi.utils

object FbUtils {
  fun convert(args: Array<String>): String {
    val fields = StringBuilder()

    for (arg in args) {
      fields.append(arg).append(',')
    }

    return removeLastComma(fields.toString())
  }

  private fun removeLastComma(value: String): String {
    var str = value
    str = str.trim { it <= ' ' }

    if (str.isNotEmpty() && str[str.length - 1] == ',') {
      str = str.substring(0, str.length - 1)
    }

    return str
  }
}
