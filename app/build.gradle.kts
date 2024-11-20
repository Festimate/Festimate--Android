plugins {
    alias(libs.plugins.festimate.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.mtc.festimate"
}

dependencies {
    implementation(projects.core.buildconfig)
    implementation(projects.core.network)
    implementation(projects.core.data)
}
