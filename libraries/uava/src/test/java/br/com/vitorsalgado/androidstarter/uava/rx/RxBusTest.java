package br.com.vitorsalgado.androidstarter.uava.rx;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.vitorsalgado.androidstarter.testutils.RxUtils;
import io.reactivex.observers.DisposableObserver;

@RunWith(JUnit4.class)
public class RxBusTest {
	@Rule
	public final RxUtils.ImmediateSchedulersRule schedulers = new RxUtils.ImmediateSchedulersRule();

	@Test(timeout = 1500)
	public void shouldRespondToEventIfAttached() {
		RxBus.getInstance().register(TestEvent.class, new DisposableObserver<TestEvent>() {
			@Override
			public void onNext(TestEvent testEvent) {
				Assert.assertNotNull(testEvent);
			}

			@Override
			public void onError(Throwable e) {
				throw new RuntimeException(e);
			}

			@Override
			public void onComplete() {

			}
		});

		RxBus.getInstance().send(new TestEvent());
		Assert.assertTrue(RxBus.getInstance().hasObservers());
	}

	class TestEvent {

	}
}
