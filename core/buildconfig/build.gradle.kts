plugins {
    alias(libs.plugins.festimate.android.library)
    alias(libs.plugins.festimate.android.hilt)
    alias(libs.plugins.festimate.plugin.test)
    alias(libs.plugins.festimate.plugin.build.config)
}

android {
    namespace = "com.mtc.buildconfig"
}

dependencies {
    implementation(projects.core.common)
}
