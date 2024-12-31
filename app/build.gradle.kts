plugins {
    alias(libs.plugins.android.application)
    id("jacoco")
    //id ("com.android.application")
    id("com.google.relay") version "0.3.12"
}

android {
    namespace = "com.pim.planta"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pim.planta"
        minSdk = 33
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
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"

    }
    buildToolsVersion = "35.0.0"


}

dependencies {
    
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.common)
    implementation(libs.room.runtime.android)
    implementation(libs.room.common.jvm)
    implementation("androidx.room:room-runtime:2.5.0")
    implementation(libs.databinding.adapters)
    implementation(libs.places)
    annotationProcessor("androidx.room:room-compiler:2.5.0")

    testImplementation(libs.junit)
    testImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.mpandroidchart)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("androidx.work:work-runtime:2.8.0")

    implementation ("androidx.compose.ui:ui")
    implementation ("androidx.compose.material:material")
    implementation ("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.runtime:runtime:1.5.3")

    implementation ("com.google.android.material:material:1.9.0")



    debugImplementation ("androidx.compose.ui:ui-tooling:1.5.3")

}

jacoco {
    toolVersion = "0.8.10"
}
