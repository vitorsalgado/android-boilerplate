object Dependencies {
  val kotlinStandardLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

  val supportAnnotations = "com.android.support:support-annotations:${Versions.supportLibrary}"
  val supportDesign = "com.android.support:design:${Versions.supportLibrary}"
  val supportRecyclerView = "com.android.support:recyclerview-v7:${Versions.supportLibrary}"
  val supportV4 = "com.android.support:support-v4:${Versions.supportLibrary}"
  val supportAppCompatV7 = "com.android.support:appcompat-v7:${Versions.supportLibrary}"
  val supportV13 = "com.android.support:support-v13:${Versions.supportLibrary}"
  val supportConstraintLayout = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"

  val firebaseCore = "com.google.firebase:firebase-core:${Versions.firebase_core}"
  val firebaseMessaging = "com.google.firebase:firebase-messaging:${Versions.firebase_messaging}"
  val firebaseConfig = "com.google.firebase:firebase-config:${Versions.firebase_remote_config}"

  val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebook_sdk}"
  val playServicesBase = "com.google.android.gms:play-services-base:${Versions.play_services}"
  val gson = "com.google.code.gson:gson:${Versions.gson}"
  val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
  val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
  val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
  val retrofitRxJavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
  val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
  val timber = "com.jakewharton.timber:timber:${Versions.timber}"
  val fresco = "com.facebook.fresco:fresco:${Versions.fresco}"
  val rootBeer = "com.scottyab:rootbeer-lib:${Versions.rootbeer}"
  val licensesDialog = "de.psdev.licensesdialog:licensesdialog:${Versions.licensesDialog}"
  val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"
  val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
  val leakCanaryNoOp = "com.squareup.leakcanary:leakcanary-android-no-op:${Versions.leakCanary}"
  val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
  val stethoOkHttp3 = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
  val traceur = "com.tspoon.traceur:traceur:${Versions.traceur}"
  val okhttp3LogInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

  val archExtensions = "android.arch.lifecycle:extensions:${Versions.arch}"
  val archCompiler = "android.arch.lifecycle:compiler:${Versions.arch}"

  val dagger = "com.google.dagger:dagger-android:${Versions.dagger}"
  val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
  val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
  val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object TestDependencies {
  val junit = "junit:junit:${Versions.junit}"
  val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
  val mockito = "org.mockito:mockito-core:${Versions.mockito}"
  val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockito}"
  val hamcrestAll = "org.hamcrest:hamcrest-all:${Versions.hamcrest}"
  val requestMatcher = "br.com.concretesolutions:requestmatcher:${Versions.requestmatcher}"
  val okhttp3MockWebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
  val jsonPathAssert = "com.jayway.jsonpath:json-path-assert:${Versions.jsonpathassert}"
  val testButler = "com.linkedin.testbutler:test-butler-library:${Versions.testButler}"

  val kotlinTest = "org.jetbrains.kotlin:kotlin-test"
  val kotlinTestJUnit = "org.jetbrains.kotlin:kotlin-test-junit"

  val supportTestRunner = "com.android.support.test:runner:${Versions.testSupportLibrary}"
  val supportTestRules = "com.android.support.test:rules:${Versions.testSupportLibrary}"
  val supportTestEspressoCore = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
  val supportTestEspressoContrib = "com.android.support.test.espresso:espresso-contrib:${Versions.espresso}"
  val supportTestEspressoIntents = "com.android.support.test.espresso:espresso-intents:${Versions.espresso}"
  val supportTestEspressoWeb = "com.android.support.test.espresso:espresso-web:${Versions.espresso}"

}
