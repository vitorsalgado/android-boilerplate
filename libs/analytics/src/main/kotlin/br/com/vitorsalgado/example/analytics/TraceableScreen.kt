package br.com.vitorsalgado.example.analytics

@FunctionalInterface
interface TraceableScreen {
  @get:Screen
  val screenName: String
}
