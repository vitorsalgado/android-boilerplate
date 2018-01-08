package com.example.uava;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PreconditionsTest {
	@Test
	public void shouldThrowExceptionAndReturnProvidedMessageWhenValueIsNull() {
		try {
			Preconditions.checkNotNull(null, "the message");
		} catch (NullPointerException ex) {
			Assert.assertEquals(ex.getMessage(), "the message");
		}
	}

	@Test
	public void shouldReturnSameValueWhenNotNull() {
		String data1 = "test1";
		data1 = Preconditions.checkNotNull(data1);

		String data2 = "test2";
		data2 = Preconditions.checkNotNull(data2, "the message");

		Assert.assertEquals(data1, "test1");
		Assert.assertEquals(data2, "test2");
	}

	@Test
	public void shouldThrowExceptionWhenArgumentDoesNotSatisfyExpression() {
		try {
			Preconditions.checkArgument(false, "error message");
		} catch (IllegalArgumentException ex) {
			Assert.assertEquals(ex.getMessage(), "error message");
		}
	}

	@Test
	public void shouldNotThrowExceptionWhenArgumentSatisfyExpression() {
		Preconditions.checkArgument(true, "message");
	}

}
