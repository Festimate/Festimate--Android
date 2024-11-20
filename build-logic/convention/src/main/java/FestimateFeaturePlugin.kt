import com.mtc.convention.extension.getBundle
import com.mtc.convention.extension.getLibrary
import com.mtc.convention.extension.implementation
import com.mtc.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FestimateFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("festimate.android.compose.library")
                apply("festimate.android.hilt")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                implementation(libs.getLibrary("kotlinx.serialization.json"))
                implementation(project(":core:ui"))
                implementation(project(":core:designsystem"))
                implementation(project(":core:model"))
                implementation(project(":core:domain"))
                implementation(project(":core:navigation"))
                implementation(libs.getBundle("compose"))
            }
        }
    }
}