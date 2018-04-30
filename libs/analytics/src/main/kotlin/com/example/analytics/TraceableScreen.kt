package com.example.analytics

@FunctionalInterface
interface TraceableScreen {
  @get:Screen
  val screenName: String
}
