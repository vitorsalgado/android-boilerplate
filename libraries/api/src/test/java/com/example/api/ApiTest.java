package com.example.api;

import com.example.api.dtos.OAuthResponse;
import com.example.uava.api.ApiResponse;
import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import br.com.concretesolutions.requestmatcher.LocalTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;

@RunWith(JUnit4.class)
public class ApiTest {
	private Api api;

	@Rule
	public final RequestMatcherRule serverRule = new LocalTestRequestMatcherRule();

	@Test
	public void ensureConfigurations() throws IOException {
		initServices();
	}

	@Test
	public void test() throws IOException {
		initServices();

		serverRule
			.addResponse(
				new MockResponse().setBody(
					new Gson().toJson(
						new OAuthResponse("TOKEN", "REFRESH", "STATE", new ArrayList<>(), "TOKEN_TYPE", 10000))))
			.ifRequestMatches();

		ApiResponse<OAuthResponse> response = api.getToken("client_id", "state", "grant_type", "username", "password")
			.blockingFirst();

		Assert.assertNotNull(response);
	}

	private void initServices() throws IOException {
		String rootUrl = serverRule.url("/").toString();
		File folder = File.createTempFile("tmp", ".tmp");
		api = ApiBuilder.build(new OkHttpClient.Builder(), new Gson(), new Config(rootUrl, folder, "cache", 1024));
	}
}
