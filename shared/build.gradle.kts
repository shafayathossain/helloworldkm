plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.0"
    id("com.google.devtools.ksp") version "1.9.0-1.0.12"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-17"
    kotlin("plugin.serialization") version "1.9.0"
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.shafayat.hellowordlkm")
        }
    }

    linkSqlite.set(true)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    val sqlDelightVersion = "2.0.0"
    val ktorVersion = "2.3.3"
    val coroutineVersion = "1.7.3"

    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {

        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }

        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation("app.cash.sqldelight:primitive-adapters:$sqlDelightVersion")

                // Ktor Client
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-auth:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:android-driver:$sqlDelightVersion")
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation("app.cash.sqldelight:native-driver:$sqlDelightVersion")
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "com.shafayat.helloworldkm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}