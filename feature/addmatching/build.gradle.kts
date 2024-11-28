plugins {
    alias(libs.plugins.festimate.feature)
}

android {
    namespace = "com.mtc.addmatching"
}

dependencies {
    implementation(projects.core.datastore)
}
