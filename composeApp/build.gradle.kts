import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.google.services)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.sqldelight.android.driver)
            implementation(libs.ktor.okhttp)
            implementation(libs.koin.android)
            implementation(libs.play.services.ads)
        }
        commonMain.dependencies {
            // Default
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            // Navigation
            implementation(libs.navigation.compose)
            // Serialization
            implementation(libs.kotlinx.serialization.json)
            // Ktor
            implementation(libs.bundles.ktor.common)
            // Database
            implementation(libs.sqldelight.runtime)
            implementation(libs.sqldelight.coroutines.extensions)
            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            // DateTime
            implementation(libs.kotlinx.datetime)
            //DateTimePicker
            implementation(libs.kmp.date.time.picker)
            // ImagePicker
            implementation(libs.peekaboo.image.picker)
            implementation(libs.peekaboo.ui)
            // Coil (async image loading)
            implementation(libs.coil.compose)
            implementation(libs.coil.ktor)
            // Lottie
            implementation(libs.compottie.core)
            implementation(libs.compottie.resources)
            implementation(libs.compottie.dot)
            // Multiplatform settings (shared preferences analogue)
            implementation(libs.multiplatform.settings.no.arg)
            // Firebase
            implementation(libs.bundles.gitlive.firebase.sdk)
            // KMP Auth
            implementation(libs.kmp.auth.google)
            implementation(libs.kmp.auth.uihelper)
            implementation(libs.kmp.auth.firebase)
            // WebView
            implementation(libs.webview.multiplatform)
            // OpenAI Client
            implementation(libs.openai.client)
            // FileKit (save images to gallery cross platform)
            implementation(libs.bundles.filekit.common)
            // Notifications
            api(libs.kmpnotifier)
            // Permissions
            implementation(libs.bundles.moko.permissions)
        }
        iosMain.dependencies {
            implementation(libs.native.driver)
            implementation(libs.ktor.darwin)
        }
    }
}

android {
    namespace = "org.catstagram.trackpet"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.catstagram.trackpet"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

sqldelight {
    databases {
        create("CatDatabase") {
            packageName.set("org.catstagram.trackpet.data.local")
            srcDirs("src/commonMain/kotlin")
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}