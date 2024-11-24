plugins {
    alias(libs.plugins.festimate.android.library)
    alias(libs.plugins.festimate.android.hilt)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.mtc.datastore"
}

dependencies {
    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.datastore)
    ksp(libs.encrypted.datastore.preference.ksp)
    implementation(libs.bundles.encrypted.datastore)
}
