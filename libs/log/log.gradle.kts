plugins {
  id("idea")
  id("java-library")
  kotlin("jvm")
}

dependencies {
  implementation(Dependencies.kotlinStandardLib)

  // ---------------------------------------------------------------------------------------------
  // Test Libs
  // ---------------------------------------------------------------------------------------------
  testImplementation(TestDependencies.kotlinTest)
  testImplementation(TestDependencies.kotlinTestJUnit)
  testImplementation(TestDependencies.junit5Api)
  testRuntimeOnly(TestDependencies.junit5Engine)
  testImplementation(TestDependencies.mockito)
  testImplementation(TestDependencies.hamcrestAll)
}
