package com.example.api.graphapi.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FbUtilsTest {
	@Test
	public void convertShouldReturnParamsSeparatedByComma() {
		String[] params = Arrays.asList(Fields.ID, Fields.NAME, Fields.EMAIL, Fields.BIRTHDAY, Fields.PICTURE_LARGE).toArray(new String[]{});
		String result = FbUtils.convert(params);

		System.out.println(result);

		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertTrue("id,", result.contains("id,"));
		assertTrue("name,", result.contains("name,"));
		assertTrue(",picture", result.contains(",picture"));
	}
}
