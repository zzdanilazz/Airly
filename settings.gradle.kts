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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Airly"
include(":app")

include(":core:api")
include(":core:impl")

include(":featureApprove:api")
include(":featureApprove:impl")

include(":featureInputLiquid:api")
include(":featureInputLiquid:impl")

include(":featureStatistics:api")
include(":featureStatistics:impl")

include(":featureDashboard:api")
include(":featureDashboard:impl")

include(":featureAchievement:api")
include(":featureAchievement:impl")

include(":featureNotifications:api")
include(":featureNotifications:impl")
