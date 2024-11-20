plugins {
    alias(libs.plugins.festimate.java.library)
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.core)
}
