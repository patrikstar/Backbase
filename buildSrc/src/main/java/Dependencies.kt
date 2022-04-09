import org.gradle.api.JavaVersion

object AppVersion {
    const val code = 12
    const val name = "1.9.1"
}

object AndroidSdk {
    const val min = 16
    const val compile = 31
    const val target = compile
    const val buildTools = "31.0.0"
    val java = JavaVersion.VERSION_11
    const val jvmTarget = "11"
}

object Libs {
    const val appcompat = "androidx.appcompat:appcompat:1.0.2"

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

    const val coreKtx = "androidx.core:core-ktx:1.1.0"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2"

    const val koin_android = "io.insert-koin:koin-android:3.1.5"

    const val gson = "com.google.code.gson:gson:2.8.6"

    object Kotlin {
        const val version = "1.6.10"
        const val classpath = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
    }

    object Lifecycle {
        const val version = "2.2.0"
        const val common = "androidx.lifecycle:lifecycle-common-java8:$version"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
    }

    object Navigation {
        const val version = "2.4.1"
        const val fragment_ktx = "androidx.navigation:navigation-fragment-ktx:$version"
        const val ui_ktx = "androidx.navigation:navigation-ui-ktx:$version"
    }

    const val material = "com.google.android.material:material:1.2.1"

    const val materialDialog = "com.afollestad.material-dialogs:core:3.1.1"

    object Test {
        const val mockkVersion = "1.11.0"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val mockk2 = "io.mockk:mockk-agent-jvm:$mockkVersion"
        const val mockk_android = "io.mockk:mockk-android:$mockkVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"
        const val junit4 = "junit:junit:4.12"
        const val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
    }

    const val timber = "com.jakewharton.timber:timber:4.7.1"
}
