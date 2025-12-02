import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.androidx.room)
}
//room{
//    schemaDirectory "$projectDir/schemas"
//}
kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
    sourceSets {
        androidMain.dependencies {
            //koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.koin.compose.viewmodel)

            //ktor
            implementation(libs.ktor.client.okhttp)

        }
        iosMain.dependencies {
            //ktor
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            //koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)

            //ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)

            implementation(libs.kotlinx.serialization.json)

            //room
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }
//        dependencies{
//            ksp(libs.androidx.room.compiler)
//        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    dependencies {
        add("kspAndroid", libs.androidx.room.compiler)
        add("kspIosSimulatorArm64", libs.androidx.room.compiler)
//        add("kspIosX64", libs.androidx.room.compiler)
        add("kspIosArm64", libs.androidx.room.compiler)
        // Add any other platform target you use in your project, for example kspDesktop
    }

    namespace = "com.zzz.movie.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
