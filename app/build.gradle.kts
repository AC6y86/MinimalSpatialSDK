plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.meta.spatial.plugin")
}

android {
    namespace = "com.example.minimalspatialsdk"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    defaultConfig {
        applicationId = "com.example.minimalspatialsdk"
        minSdk = 29
        targetSdk = 34
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
    packaging {
        jniLibs.useLegacyPackaging = true
    }
    flavorDimensions += "platform"
    productFlavors {
        create("quest") {
            dimension = "platform"
            applicationIdSuffix = ".quest"
            versionNameSuffix = "-quest"
        }
    }
}

val metaSpatialSdkVersion = "0.5.5"

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.4")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.4")
    
    implementation("com.meta.spatial:meta-spatial-sdk:$metaSpatialSdkVersion")
    implementation("com.meta.spatial:meta-spatial-sdk-toolkit:$metaSpatialSdkVersion")
    implementation("com.meta.spatial:meta-spatial-sdk-vr:$metaSpatialSdkVersion")
    implementation("com.meta.spatial:meta-spatial-sdk-physics:$metaSpatialSdkVersion")
    ksp("com.meta.spatial.plugin:com.meta.spatial.plugin.gradle.plugin:0.5.5")
}

val projectDir = layout.projectDirectory
val sceneDirectory = projectDir.dir("spatial_editor/MediaApp")
spatial {
    allowUsageDataCollection = true
    scenes {
        exportItems {
            item {
                projectPath.set(sceneDirectory.file("Main.metaspatial"))
                outputPath.set(projectDir.dir("src/quest/assets/scenes"))
            }
        }
    }
}

afterEvaluate {
    tasks.named("assembleQuestDebug") {
        //dependsOn("export")
        //finalizedBy("generateComponents")
    }
}