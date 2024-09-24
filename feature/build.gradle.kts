plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.eoyeongbooyeong.feature"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"

        }
    }
}

dependencies {
    // connect module dependency
    implementation(project(":core"))
    implementation(project(":domain"))

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.collections.immutable)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.android.compiler)
    implementation(libs.hilt.android.testing)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.core)
    ksp(libs.hilt.android.compiler)

    //Timber
    implementation(libs.timber)

    // Coil
    implementation(libs.compose.coil)
    implementation(libs.coil.svg)

    //Kakao SDK
    implementation(libs.kakao.user)
    implementation(libs.kakao.map)

    //google play service
    implementation(libs.google.play.services.location)

    // Navigation
    implementation(libs.androidx.compose.navigation)

    // Phoenix
    implementation(libs.jakewharton.process.phoenix)

    implementation(libs.accompanist.systemuicontroller)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
