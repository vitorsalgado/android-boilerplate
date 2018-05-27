// package br.com.vitorsalgado.example.android.utils.base
//
// import br.com.vitorsalgado.example.toolkit.Preconditions.checkNotNull
// import io.reactivex.disposables.CompositeDisposable
// import io.reactivex.disposables.Disposable
//
// abstract class ViewPresenter<V : PresenterView> protected constructor(view: V) {
//  private val mCompositeSubscription = CompositeDisposable()
//  protected var view: V? = null
//    private set
//
//  init {
//    checkNotNull(view, "view cannot be null.")
//    this.view = view
//  }
//
//  fun bind(view: V) {
//    checkNotNull(view, "view cannot be null.")
//    this.view = view
//  }
//
//  fun release() {
//    clearSubscriptions()
//    view = null
//  }
//
//  protected fun addSubscription(disposable: Disposable) {
//    mCompositeSubscription.add(disposable)
//  }
//
//  fun clearSubscriptions() {
//    mCompositeSubscription.clear()
//  }
// }
