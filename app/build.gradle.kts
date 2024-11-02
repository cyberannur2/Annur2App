plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("kotlin-parcelize")
    id ("com.google.devtools.ksp") version "1.9.0-1.0.13"
}

android {
    namespace = "com.miniawradsantri.awrad"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.miniawradsantri.awrad"
        minSdk = 34
        targetSdk = 34
        versionCode = 15
        versionName = "3.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
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
        viewBinding = true
    }
}

dependencies {
    // Core Android libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Navigation components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.storage)

    // RecyclerView
    implementation(libs.androidx.recyclerview)

    // Lifecycle components
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.extensions)

    // Glide (Image Loading)
    implementation(libs.glide)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
    annotationProcessor(libs.compiler)

    // Retrofit (Networking)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Room (Database)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.fragment.ktx)

    //Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    //shimmer
    implementation (libs.shimmer)

    //Pdf Viewer
    implementation(libs.androidpdfviewer)
    implementation(libs.prdownloader)

//    implementation("dev.chrisbanes.insetter:insetter-ktx:0.6.1")
}