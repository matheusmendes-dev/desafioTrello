plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleDaggerHilt)
    alias(libs.plugins.jetbrainsKotlinKapt)
}

android {
    namespace = "com.mendev.trello"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mendev.trello"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_BASE_URL", "\"${System.getenv("TRELLO_API_BASE_URL")}\"")
            buildConfigField("String", "API_KEY", "\"${System.getenv("TRELLO_API_KEY")}\"")
            buildConfigField("String", "API_TOKEN", "\"${System.getenv("TRELLO_API_TOKEN")}\"")
        }
        release {
            isMinifyEnabled = false

            buildConfigField("String", "API_BASE_URL", "\"${System.getenv("TRELLO_API_BASE_URL")}\"")
            buildConfigField("String", "API_KEY", "\"${System.getenv("TRELLO_API_KEY")}\"")
            buildConfigField("String", "API_TOKEN", "\"${System.getenv("TRELLO_API_TOKEN")}\"")

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
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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
    implementation(libs.androidx.navigation)

    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.gson)
    implementation(libs.squareup.okhttp3.okhttp)
    implementation(libs.squareup.okhttp3.logging.interceptor)

    implementation(libs.google.dagger.hilt)
    kapt(libs.google.dagger.hilt.compiler)

    implementation(libs.google.code.gson)

    testImplementation(libs.junit)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}