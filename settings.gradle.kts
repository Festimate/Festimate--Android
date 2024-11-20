enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Festimate"
include(":app")
include(":core:network")
include(":core:common")
include(":core:data")
include(":core:designsystem")
include(":core:model")
include(":core:ui")
include(":core:buildconfig")
include(":core:domain")
include(":core:navigation")
include(":feature:main")
include(":feature:signup")
include(":feature:home")
include(":feature:addmatching")
