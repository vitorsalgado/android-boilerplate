package br.com.vitorsalgado.androidstarter.uava.rx;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

public final class RxBus {
	private static final RxBus INSTANCE = new RxBus();
	private final PublishSubject<Object> mEventBus = PublishSubject.create();

	public static RxBus getInstance() {
		return INSTANCE;
	}

	public void send(final Object event) {
		mEventBus.onNext(event);
	}

	@SuppressWarnings("unchecked")
	public <T> Disposable register(final Class<T> eventClass, DisposableObserver<T> onNext) {
		return mEventBus
				.filter(o -> eventClass.isAssignableFrom(o.getClass()))
				.map(o -> (T) o)
				.subscribeWith(onNext);
	}

	public Observable<Object> toObservable() {
		return mEventBus;
	}

	public boolean hasObservers() {
		return mEventBus.hasObservers();
	}
}
