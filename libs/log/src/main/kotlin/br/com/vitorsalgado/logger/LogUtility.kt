package br.com.vitorsalgado.logger

object LogUtility {
  private val subscriber = mutableListOf<Log>()

  fun attach(observer: Log) {
    if (subscriber.contains(observer))
      throw IllegalArgumentException("observer of type ${observer.javaClass.simpleName} already added")

    subscriber.add(observer)
  }

  fun detach(observer: Log) {
    subscriber.remove(observer)
  }

  fun detachAll() {
    subscriber.clear()
  }
}
