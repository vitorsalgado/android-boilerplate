package br.com.vitorsalgado.example.api

import br.com.concretesolutions.requestmatcher.LocalTestRequestMatcherRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import br.com.vitorsalgado.example.api.bff.Api
import br.com.vitorsalgado.example.api.bff.ApiFactory
import br.com.vitorsalgado.example.api.bff.Config
import br.com.vitorsalgado.example.api.bff.dtos.OAuthResponse
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import java.io.File
import java.io.IOException

class ApiTest {
  @Rule
  @JvmField
  val serverRule: RequestMatcherRule = LocalTestRequestMatcherRule()
  private var api: Api? = null

  @Test
  @Throws(IOException::class)
  fun `ensure api factory configurations`() {
    initServices()
  }

  @Test
  @Throws(IOException::class)
  fun `it should be able to call the remote api as its configuration`() {
    initServices()

    serverRule
      .addResponse(
        MockResponse().setBody(
          Gson().toJson(
            OAuthResponse("TOKEN", "REFRESH", "STATE", ArrayList(), "TOKEN_TYPE", 10000))))
      .ifRequestMatches()

    val response = api!!
      .getToken("client_id", "state", "grant_type", "username", "password")
      .blockingFirst()

    assertNotNull(response)
  }

  @Throws(IOException::class)
  private fun initServices() {
    val rootUrl = serverRule.url("/").toString()
    val folder = File.createTempFile("tmp", ".tmp")
    api = ApiFactory.build(OkHttpClient.Builder(), Gson(), Config(rootUrl, folder, "cache", 1024))
  }
}
