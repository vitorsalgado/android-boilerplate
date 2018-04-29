package com.example.api.graphapi.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface FbConsts {
	List<String> FACEBOOK_USER_FIELDS = Collections.unmodifiableList(
		Arrays.asList(
			Fields.ID,
			Fields.NAME,
			Fields.FIRST_NAME,
			Fields.EMAIL,
			Fields.GENDER,
			Fields.BIRTHDAY,
			Fields.PICTURE_LARGE,
			Fields.HOMETOWN,
			Fields.ALBUMS
		)
	);
}
