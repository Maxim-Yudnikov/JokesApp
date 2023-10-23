// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("io.realm.kotlin") version "1.4.0" apply false
}

buildscript {
    dependencies {
        classpath("io.realm:realm-gradle-plugin:10.16.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8")
    }
}