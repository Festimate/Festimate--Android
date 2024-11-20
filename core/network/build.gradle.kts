plugins {
    alias(libs.plugins.festimate.android.library)
    alias(libs.plugins.festimate.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.festimate.plugin.test)
}

android {
    namespace = "com.mtc.network"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.process.phoenix)
}
