package br.com.vitorsalgado.example.utils

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

object RxBus {
  val disposables = mutableMapOf<Any, List<CompositeDisposable>>()
  val publisher: PublishSubject<Any> = PublishSubject.create()


  fun publish(event: Any) = publisher.onNext(event)

  fun <T> subscribe(eventType: Class<T>): Observable<T> = publisher.ofType(eventType)

  fun <T> register(eventType: Class<T>, subscription: CompositeDisposable) {
    val list = getDisposablesByEvent(eventType).apply {
      add(subscription)
    }

    disposables[eventType] = list
  }

  private fun <T> getDisposablesByEvent(eventType: Class<T>): MutableList<CompositeDisposable> = disposables[eventType].orEmpty().toMutableList()
}
