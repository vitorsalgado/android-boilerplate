package com.example.android.utils;

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
    server.url("/");
  }

  @After
  public void baseTearDown() {
  }
}
