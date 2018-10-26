package br.com.vitorsalgado.logger

import java.util.*

data class LogEventArgs(val message: String, val exception: Exception, val timestamp: Date = Date())
