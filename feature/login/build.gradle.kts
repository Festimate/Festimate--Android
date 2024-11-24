plugins {
    alias(libs.plugins.festimate.feature)
}

android {
    namespace = "com.mtc.login"
}

dependencies {
    implementation(projects.core.datastore)
}
