plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.devtools)
    alias(libs.plugins.hilt)
    alias(libs.plugins.android.junit5)
}

android {
    namespace = "com.orange.newly.data"
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
        buildConfig = true
    }
}

dependencies {
    implementation(projects.domain)

    implementation(platform(libs.result4k.bom))
    implementation(libs.result4k)
    implementation(libs.core.ktx)
    implementation(libs.hilt)
    implementation(libs.bundles.room)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.paging)

    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.room.test)
    testImplementation(libs.paging.testing)
    testImplementation(libs.bundles.testing)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}