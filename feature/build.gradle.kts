plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.orange.newly.ui"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    }


}

dependencies {
    implementation(projects.domain)

    implementation(platform(libs.compose.bom))
    implementation(platform(libs.result4k.bom))

    implementation(libs.result4k)
    implementation(libs.core.ktx)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.hilt)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.coil)
    implementation(libs.bundles.paging)

    ksp(libs.hilt.compiler)

    debugImplementation(libs.bundles.compose.debug)

    testImplementation(libs.junit)
    testImplementation(libs.bundles.compose.test)

    androidTestImplementation(libs.bundles.compose.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}