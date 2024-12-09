
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

apply(plugin = "com.android.application")
apply(plugin = "org.jetbrains.kotlin.android")
apply(plugin = "androidx.navigation.safeargs.kotlin")
apply(plugin = "kotlinx-serialization")

android {
    namespace = "com.example.lab_5"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.lab_5"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true // Включаем View Binding
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
}

dependencies {

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.7.0")

    // Retrofit converter for Kotlinx Serialization
    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    // Ktor Client dependencies
    implementation("io.ktor:ktor-client-core:2.2.3")
    implementation("io.ktor:ktor-client-android:2.2.3")
    implementation("io.ktor:ktor-client-serialization:2.2.3")
    implementation("io.ktor:ktor-client-logging:2.2.3")

    // Retrofit dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")


    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    // Picasso for image loading
    implementation("com.squareup.picasso:picasso:2.8")

    // SLF4J dependencies for logging (optional)
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.slf4j:slf4j-android:1.7.36")

    // Material design and UI libraries
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.compose.ui:ui:1.7.2")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.2")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Navigation dependencies
    implementation("androidx.navigation:navigation-fragment:2.8.4")
    implementation("androidx.navigation:navigation-ui:2.8.4")

    // Testing libraries
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.2")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.7.2")

    // Ktor ContentNegotiation and JSON Serialization dependencies
    implementation("io.ktor:ktor-client-content-negotiation:2.2.3")  // Для использования ContentNegotiation
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.3") // Для сериализации JSON с kotlinx.serialization
}



