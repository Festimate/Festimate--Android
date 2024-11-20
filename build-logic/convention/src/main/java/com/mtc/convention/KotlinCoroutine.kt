package com.mtc.convention

import com.android.build.api.dsl.CommonExtension
import com.mtc.convention.extension.getBundle
import com.mtc.convention.extension.implementation
import com.mtc.convention.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlinCoroutine(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            implementation(libs.getBundle("coroutine"))
        }
    }
}
