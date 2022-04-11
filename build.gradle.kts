buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://jitpack.io"))
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
        classpath("com.google.gms:google-services:3.0.0")
    }
}

plugins {
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://jitpack.io"))
        flatDir {
            dirs("$rootDir/aars")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
