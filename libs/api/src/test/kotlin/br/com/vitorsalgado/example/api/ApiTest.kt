package br.com.vitorsalgado.example.api

import br.com.concretesolutions.requestmatcher.LocalTestRequestMatcherRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import br.com.vitorsalgado.example.api.gateway.Api
import br.com.vitorsalgado.example.api.gateway.ApiBuilder
import br.com.vitorsalgado.example.api.gateway.Config
import br.com.vitorsalgado.example.api.gateway.dtos.OAuthResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.File
import java.io.IOException

@RunWith(JUnit4::class)
class ApiTest {
  @Rule
  @JvmField
  val serverRule: RequestMatcherRule = LocalTestRequestMatcherRule()
  private var api: Api? = null

  @Test
  @Throws(IOException::class)
  fun ensureConfigurations() {
    initServices()
  }

  @Test
  @Throws(IOException::class)
  fun test() {
    initServices()

    serverRule
      .addResponse(
        MockResponse().setBody(
          Gson().toJson(
            OAuthResponse("TOKEN", "REFRESH", "STATE", ArrayList(), "TOKEN_TYPE", 10000))))
      .ifRequestMatches()

    val response = api!!.getToken("client_id", "state", "grant_type", "username", "password")
      .blockingFirst()

    Assert.assertNotNull(response)
  }

  @Throws(IOException::class)
  private fun initServices() {
    val rootUrl = serverRule.url("/").toString()
    val folder = File.createTempFile("tmp", ".tmp")
    api = ApiBuilder.build(OkHttpClient.Builder(), Gson(), Config(rootUrl, folder, "cache", 1024))
  }
}
