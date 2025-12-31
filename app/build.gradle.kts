plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.assignment1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.assignment1"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    useLibrary("wear-sdk")
}

dependencies {
    implementation(libs.play.services.wearable)
    implementation(libs.core.splashscreen)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.wear:wear:1.3.0")
}