plugins {
    alias(libs.plugins.festimate.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.festimate.android.hilt)
}

android {
    namespace = "com.mtc.festimate"
}

dependencies {
    implementation(projects.feature.main)
    implementation(projects.core.buildconfig)
    implementation(projects.core.network)
    implementation(projects.core.data)
}
