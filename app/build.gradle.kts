import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.productdescapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.productdescapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.productdescapp.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE*"
            excludes += "META-INF/NOTICE*"
        }
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.litert.support.api)
    implementation(libs.core)
    implementation(libs.play.services.analytics.impl)
    implementation(libs.androidx.biometric.ktx)
    implementation(libs.hilt.android.testing)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.ui.test.junit4.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Jetpack Compose
    implementation(libs.ui)
    implementation (libs.androidx.material)
    implementation (libs.ui.tooling.preview)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Coroutines
    implementation (libs.kotlinx.coroutines.android)

    // Mockk for unit testing
    testImplementation(libs.mockk)
    // Mockk for UI testing
    androidTestImplementation (libs.mockk.android)


    // Retrofit for network requests
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Jetpack Compose Navigation
    implementation(libs.androidx.navigation.compose.v253)

    // Hilt for ViewModels in Compose
    implementation(libs.androidx.hilt.navigation.compose)

    implementation (libs.androidx.material.icons.extended)// Required for extended icons
    implementation (libs.activity.compose)
    // state flow
    implementation (libs.androidx.lifecycle.runtime.ktx)// For StateFlow

    // Coil for Jetpack Compose
    implementation(libs.coil.compose)

    testImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation (libs.androidx.junit.v113)
    androidTestImplementation(libs.hilt.android.testing) // Or latest version
    kaptAndroidTest(libs.hilt.android.compiler.v244)

    implementation(libs.androidx.biometric.ktx)
    implementation(libs.androidx.biometric)// or the latest stable version
    implementation(libs.androidx.activity.ktx) // or the latest stable version
    implementation("androidx.appcompat:appcompat:1.6.1") // Check for the latest version
    androidTestImplementation("androidx.biometric:biometric-ktx:1.1.0")

    // ... other dependencies ...
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44") // Or latest version
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.44")

}