plugins {
    alias(libs.plugins.festimate.feature)
}

android {
    namespace = "com.mtc.main"
}

dependencies {
    implementation(projects.feature.signup)
    implementation(projects.feature.home)
    implementation(projects.feature.addmatching)
}
