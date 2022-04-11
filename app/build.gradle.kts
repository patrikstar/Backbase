plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = AndroidSdk.compile
    buildToolsVersion = AndroidSdk.buildTools

    defaultConfig {
        applicationId = "com.backbase"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = AppVersion.code
        versionName = AppVersion.name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        testProguardFile("proguard-rules-test.pro")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isJniDebuggable = true
            isRenderscriptDebuggable = true
            isMinifyEnabled = false
            buildConfigField("boolean", "LOG_ENABLE", "true")
            applicationIdSuffix = ".debug"
        }

        getByName("release") {
            isDebuggable = false
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isMinifyEnabled = true
            buildConfigField("boolean", "LOG_ENABLE", "true")
        }
    }

    lint {
        isAbortOnError = false
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = AndroidSdk.java
        targetCompatibility = AndroidSdk.java
    }

    kotlinOptions {
        jvmTarget = AndroidSdk.jvmTarget
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    implementation(Libs.Kotlin.stdlib)

    implementation(Libs.appcompat)
    implementation(Libs.coreKtx)
    implementation(Libs.Lifecycle.viewModel)
    implementation(Libs.Lifecycle.livedata)

    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.materialDialog)
    implementation("com.google.android.gms:play-services-maps:18.0.2")

    implementation(Libs.kotlin_serialization)

    implementation(Libs.coroutines)
    implementation(Libs.coroutines_core)

    implementation(Libs.koin_android)

    implementation(Libs.timber)

    implementation(Libs.Navigation.fragment_ktx)
    implementation(Libs.Navigation.ui_ktx)

    testImplementation(kotlin("test"))
    testImplementation(Libs.Test.junit4)
    testImplementation(Libs.Test.mockk)
    testImplementation(Libs.Test.coroutines)
    testImplementation(Libs.Test.arch_core)
    testImplementation(Libs.Test.mockito)
    androidTestImplementation(Libs.Test.mockk_android)
    androidTestImplementation(Libs.Test.androidx_junit)
    androidTestImplementation(Libs.Test.espresso)
}
