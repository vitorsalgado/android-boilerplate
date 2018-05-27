package br.com.vitorsalgado.example.utils

import br.com.concretesolutions.requestmatcher.LocalTestRequestMatcherRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseRobotTest {
  @Rule
  val server: RequestMatcherRule = LocalTestRequestMatcherRule()

  @Before
  fun baseSetUp() {
    server.url("/")
  }

  @After
  fun baseTearDown() {
  }
}
