plugins {
  id("com.android.library")
  kotlin("android")
}

dependencies {
  // ---------------------------------------------------------------------------------------------
  // Kotlin
  // ---------------------------------------------------------------------------------------------
  implementation(Dependencies.kotlinStandardLib)

  // ---------------------------------------------------------------------------------------------
  // Libraries
  // ---------------------------------------------------------------------------------------------
  implementation(Dependencies.appcompat) {
    exclude(module = "support-annotations")
    exclude(module = "support-v4")
    exclude(module = "cardview-v7")
  }
  implementation(Dependencies.design) {
    exclude(module = "support-v4")
    exclude(module = "appcompat-v7")
    exclude(module = "recyclerview-v7")
    exclude(module = "cardview-v7")
  }
  implementation(Dependencies.recyclerView) {
    exclude(module = "support-annotations")
    exclude(module = "support-compat")
    exclude(module = "support-core-ui")
  }
  implementation(Dependencies.playServicesBase)
  implementation(Dependencies.rxAndroid)
  implementation(Dependencies.rxJava)
  implementation(Dependencies.timber)

  // ---------------------------------------------------------------------------------------------
  // Test Libs
  // ---------------------------------------------------------------------------------------------
  testImplementation(TestDependencies.junit)
  testImplementation(TestDependencies.kotlinTest)
  testImplementation(TestDependencies.kotlinTestJUnit)
  testImplementation(TestDependencies.robolectric)
  testImplementation(TestDependencies.okhttp3MockWebserver)
  testImplementation(TestDependencies.requestMatcher)
}
