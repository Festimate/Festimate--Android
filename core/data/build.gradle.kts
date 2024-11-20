plugins {
    alias(libs.plugins.festimate.data)
}

android {
    namespace = "com.mtc.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
}
