plugins {
     `kotlin-dsl`
}

group = "org.mtc.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.compose.compiler.extension)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "festimate.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }

        register("androidLibrary") {
            id = "festimate.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }

        register("androidComposeLibrary") {
            id = "festimate.android.compose.library"
            implementationClass = "AndroidComposeLibraryPlugin"
        }

        register("androidHilt") {
            id = "festimate.android.hilt"
            implementationClass = "HiltPlugin"
        }

        register("javaLibrary") {
            id = "festimate.java.library"
            implementationClass = "JavaLibraryPlugin"
        }

        register("buildConfig") {
            id = "festimate.plugin.build.config"
            implementationClass = "BuildConfigPlugin"
        }

        register("androidTest") {
            id = "festimate.plugin.android.test"
            implementationClass = "AndroidTestPlugin"
        }

        register("unitTest") {
            id = "festimate.plugin.test"
            implementationClass = "UnitTestPlugin"
        }

        register("festimateFeature") {
            id = "festimate.feature"
            implementationClass = "FestimateFeaturePlugin"
        }

        register("festimateData") {
            id = "festimate.data"
            implementationClass = "FestimateDataPlugin"
        }
    }
}