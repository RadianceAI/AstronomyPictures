plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android") version "1.9.10"
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "by.radiance.space.pictures.presenter"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "by.radiance.space.pictures.presenter"
}

dependencies {
    implementation(project(":util"))
    implementation(project(":remote"))
    implementation(project(":domain"))
    implementation(project(":local"))
    implementation(project(":today"))

    implementation(libs.core.ktx)
    implementation(libs.ui.ui)
    implementation(libs.androidx.material.material)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.ui.tooling)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.fragment)
    implementation(libs.androidx.animation)
    implementation(libs.ui.tooling)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //koin
    implementation(libs.koin.android.core)
    implementation(libs.koin.android.compat)
    implementation(libs.koin.androidx.workmanager)
    implementation(libs.koin.androidx.navigation)
    implementation(libs.koin.androidx.compose)

    //coroutines
    //noinspection GradleDependency
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //glide
    implementation(libs.glide)

    //datastore
    implementation(libs.androidx.datastore.database)
    implementation(libs.androidx.datastore.preferences)

    //coil
    implementation(libs.coil.compose)

    //gson
    implementation(libs.gson)

    //work manager
    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.viewbinding)
    implementation(libs.multidex)

    implementation(libs.androidx.paging.compose)
}