pluginManagement {
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
        maven("https://jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "MangoTest"
include(":app")
include(":data")
include(":common")
include(":feature-auth")
include(":feature-main")
include(":feature-auth:presentation")
include(":feature-auth:domain")
include(":feature-main:presentation")
include(":feature-main:domain")
include(":core-ui")
