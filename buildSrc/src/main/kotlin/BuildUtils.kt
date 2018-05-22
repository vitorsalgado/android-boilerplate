object BuildUtils {
  val cores: Int = Runtime.getRuntime().availableProcessors().div(2)
  val isCI: Boolean = System.getenv("CI") == "true"
}
