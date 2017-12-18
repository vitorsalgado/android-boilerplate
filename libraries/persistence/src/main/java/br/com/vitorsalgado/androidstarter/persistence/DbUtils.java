package br.com.vitorsalgado.androidstarter.persistence;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class DbUtils {
	public static String toCommaDelimitedString(@NonNull List<String> args) {
		StringBuilder fields = new StringBuilder();

		for (String arg : args) {
			fields.append(arg).append(",");
		}

		return removeLastComma(fields.toString());
	}

	public static List<String> fromString(@NonNull String arg) {
		return Arrays.asList(arg.split(","));
	}

	private static String removeLastComma(String str) {
		str = str.trim();

		if (str.length() > 0 && str.charAt(str.length() - 1) == ',') {
			str = str.substring(0, str.length() - 1);
		}

		return str;
	}
}
