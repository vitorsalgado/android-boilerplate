package com.example.toolkit;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(JUnit4.class)
public class OptionalTest {
	@Test
	public void isPresentShouldReturnFalseWhenNullOptueIsProvided() {
		Optional maybe = Optional.fromNullable(null);

		assertThat(maybe.isPresent(), Is.is(false));
		assertThat(maybe.isNotPresent(), Is.is(true));
	}

	@Test
	public void isPresentShouldReturnTrueWhenOptidOptueIsProvided() {
		Optional maybe = Optional.from("test data");

		assertThat(maybe.isPresent(), Is.is(true));
		assertThat(maybe.isNotPresent(), Is.is(false));
	}

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenCallingFromWithNullOptue() {
		Optional.from(null);
	}

	@Test
	public void emptyShouldReturnInstanceWithoutData() {
		Optional maybe = Optional.empty();

		assertThat(maybe.isPresent(), Is.is(false));
		assertThat(maybe.isNotPresent(), Is.is(true));
	}

	@Test
	public void getShouldReturnProvidedData() {
		Optional<String> maybe = Optional.from("test");
		String data = maybe.get();

		MatcherAssert.assertThat(data, Is.is("test"));
	}

	@Test
	public void getOrDefaultShouldReturnTheDefaultOptueWhenMaybeIsEmpty() {
		Optional<String> maybe = Optional.fromNullable(null);
		String data = maybe.getOrDefault("test");

		MatcherAssert.assertThat(data, Is.is("test"));
	}

	@Test
	public void getOrDefaultShouldReturnProvidedDataWhenMaybeIsNotEmpty() {
		Optional<String> maybe = Optional.fromNullable("test");
		String data = maybe.getOrDefault("test 2");

		MatcherAssert.assertThat(data, Is.is("test"));
	}
}
