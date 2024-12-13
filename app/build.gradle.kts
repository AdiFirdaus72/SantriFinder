plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.example.santrifinder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.santrifinder"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // Konfigurasi tambahan jika menggunakan file resources modern (linting lebih ketat)
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jika menggunakan Glide untuk gambar
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

    // Jika menggunakan library CameraX (untuk fitur kamera)
    implementation("androidx.camera:camera-core:1.3.0")
    implementation("androidx.camera:camera-view:1.3.0")
    implementation("androidx.camera:camera-lifecycle:1.3.0")

    // Jika menggunakan RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    // AndroidX Core
    implementation("androidx.core:core-ktx:1.12.0")

    // Material Components (untuk DatePicker dan TimePicker dialog bawaan Material Design)
    implementation("com.google.android.material:material:1.10.0")

    // SQLite database helper
    implementation("androidx.sqlite:sqlite:2.3.0")
}
