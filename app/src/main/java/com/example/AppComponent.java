package com.example;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
	AndroidInjectionModule.class,
	AppModule.class
})
public interface AppComponent {
	@Component.Builder
	interface Builder {
		@BindsInstance
		Builder application(Application application);

		AppComponent build();
	}

	void inject(App application);
}
