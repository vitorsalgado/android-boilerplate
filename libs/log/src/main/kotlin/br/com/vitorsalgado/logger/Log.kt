package br.com.vitorsalgado.logger

interface Log {
  fun log(sender: Any, event: LogEventArgs)

  override fun hashCode(): Int

  override fun equals(other: Any?): Boolean
}
