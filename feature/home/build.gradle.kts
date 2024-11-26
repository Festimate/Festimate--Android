plugins {
    alias(libs.plugins.festimate.feature)
}

android {
    namespace = "com.mtc.home"
}

dependencies {
    implementation(projects.core.datastore)
}
