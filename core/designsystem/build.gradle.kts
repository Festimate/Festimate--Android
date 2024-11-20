plugins {
    alias(libs.plugins.festimate.android.compose.library)
}

android {
    namespace = "com.mtc.designsystem"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.model)
}
