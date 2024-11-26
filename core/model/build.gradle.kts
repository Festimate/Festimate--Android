plugins {
    alias(libs.plugins.festimate.java.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
