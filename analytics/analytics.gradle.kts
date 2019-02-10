plugins {
  id("com.android.library")
  kotlin("android")
}

dependencies {
  implementation(Dependencies.kotlinStandardLib)
  implementation(Dependencies.firebaseCore)

  testImplementation(TestDependencies.junit)
  testImplementation(TestDependencies.mockito)
}
