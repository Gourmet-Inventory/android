import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.20"
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.gourmet_inventory_mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gourmet_inventory_mobile"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val localProperties = project.rootProject.file("local.properties")
        val properties = Properties().apply {
            load(FileInputStream(localProperties))
        }

        buildConfigField(
            type = "String",
            name = "API_BASE_URL",
            value = properties.getProperty("API_BASE_URL")
        )

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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true

    }
}

dependencies {

    implementation(libs.androidx.constraintlayout)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Navigation Compose dependencies
    implementation(libs.navigation.compose)
    //Compose Serialization
    implementation(libs.kotlinx.serialization.json)
    //Network calls
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //Json to Kotling object mapping
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Image loading
    implementation(libs.coil.compose)
    //Logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")
    //Datastore
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    //Koin
    implementation("io.insert-koin:koin-android:4.0.0")
    implementation("io.insert-koin:koin-compose-viewmodel:4.0.0")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.room.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}