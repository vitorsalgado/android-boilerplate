package com.example.features;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.example.toolkit.Preconditions.checkNotNull;

public abstract class ViewPresenter<V extends PresenterView> {
  private final CompositeDisposable mCompositeSubscription = new CompositeDisposable();
  private V mView;

  protected ViewPresenter(@NonNull V view) {
    checkNotNull(view, "view cannot be null.");
    mView = view;
  }

  public void bind(@NonNull V view) {
    checkNotNull(view, "view cannot be null.");
    mView = view;
  }

  public void release() {
    clearSubscriptions();
    mView = null;
  }

  protected V getView() {
    return mView;
  }

  protected void addSubscription(Disposable disposable) {
    mCompositeSubscription.add(disposable);
  }

  public void clearSubscriptions() {
    mCompositeSubscription.clear();
  }
}
