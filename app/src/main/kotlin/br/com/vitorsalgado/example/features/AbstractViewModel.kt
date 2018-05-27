package br.com.vitorsalgado.example.features

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbstractViewModel : ViewModel() {
  private val mCompositeSubscription = CompositeDisposable()

  protected fun addSubscription(disposable: Disposable) {
    mCompositeSubscription.add(disposable)
  }

  override fun onCleared() {
    mCompositeSubscription.clear()
    super.onCleared()
  }
}
