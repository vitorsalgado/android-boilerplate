package com.example.features;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.example.utils.espresso.EspressoIdlingResource;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.utils.Preconditions.checkNotNull;

public abstract class BasePresenter<V extends BaseView> {
    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();
    protected Scheduler mBackScheduler = Schedulers.io();
    protected Scheduler mUiScheduler = AndroidSchedulers.mainThread();
    private V mView;

    protected BasePresenter(@NonNull V view) {
        checkNotNull(view, "view cannot be null.");
        mView = view;
    }

    public EventBus getEventBus() {
        return EventBus.getDefault();
    }

    public void unsubscribe() {
        if (getEventBus().isRegistered(this)) {
            getEventBus().unregister(this);
        }

        if (!mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.clear();
        }

        mView = null;
    }

    protected V getView() {
        return mView;
    }

    protected String getTag() {
        return this.getClass().getSimpleName();
    }

    public <R> void subscribeOnIO(
            @NonNull Observable<R> observable,
            @NonNull Consumer<R> onNext) {

        EspressoIdlingResource.increment();

        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(mUiScheduler)
                .subscribe(onNext, ex -> Log.e(getTag(), ex.getMessage(), ex)));
    }

    @SuppressWarnings("unchecked")
    public <R> void subscribeOnIO(
            @NonNull Observable<R> observable,
            @NonNull DisposableObserver subscription) {

        EspressoIdlingResource.increment();

        mCompositeSubscription.add(observable
                .subscribeOn(mBackScheduler)
                .observeOn(mUiScheduler)
                .subscribeWith(subscription));
    }

    @VisibleForTesting
    void changeSchedulers(@NonNull Scheduler backScheduler, @NonNull Scheduler uiScheduler) {
        checkNotNull(backScheduler, "backScheduler cannot be null.");
        checkNotNull(uiScheduler, "uiScheduler cannot be null.");

        mBackScheduler = backScheduler;
        mUiScheduler = uiScheduler;
    }
}
