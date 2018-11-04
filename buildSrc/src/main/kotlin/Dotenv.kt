//import java.io.File
//
//object Dotenv {
//fun load() {
//  val envFile = ".env"
//  val env: MutableMap<String, String> = mutableMapOf()
//
//  val file = File("${rootDir}/${envFilev}")
//
//  if(!file.exists()) {
//    println("******************************")
//    println("no .env file found")
//    return
//  }
//
//  file.forEachLine { line ->
//    val matcher = line.matches(/^\s*(?:export\s+|)([\w\d.\-_]+)\s*=\s*['"]?(.*?)?['"]?\s*$/))
//
//    if (matcher.getCount() == 1 && matcher[0].size() == 3) {
//      env.put((matcher[0][1]), matcher[0][2].replace('"', '\\"'))
//    }
//  }
//
//  project.ext.set('env', env)
//}
//
//def apply() {
//  android {
//    defaultConfig {
//      project.env.each { k, v ->
//        def escaped = v.replaceAll("%","\\\\u0025")
//        buildConfigField "String", k, "\"$v\""
//        resValue "string", k, "\"$escaped\""
//      }
//    }
//  }
//}
//}
