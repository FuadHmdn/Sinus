plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.fuad.sinus"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fuad.sinus"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "\"https://api.bmkg.go.id/\"")
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    // Gson Converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Logging Interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")

    implementation("androidx.compose.runtime:runtime-livedata:1.7.8")

    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.coil-kt:coil-svg:2.5.0")

    implementation("com.google.accompanist:accompanist-placeholder-material:0.32.0")

    implementation("com.google.dagger:hilt-android:2.50")

    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
}