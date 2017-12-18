package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import br.com.concretesolutions.requestmatcher.LocalTestRequestMatcherRule;
import br.com.concretesolutions.requestmatcher.RequestMatcherRule;

public abstract class BaseRobotTest {
	@Rule
	public final RequestMatcherRule server = new LocalTestRequestMatcherRule();

	@Before
	public void baseSetUp() {
		String serverURL = server.url("/").toString();
		System.out.println(String.format("Server URL: %s", serverURL));
	}

	@After
	public void baseTearDown() {
	}
}
