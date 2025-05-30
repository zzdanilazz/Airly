@file:Suppress("unused")

package ru.health.airly.tab.impl

import dagger.Binds
import dagger.Module
import ru.health.airly.tab.api.TabComponent
import ru.health.featuredashboard.DashboardFeatureModule
import ru.health.featurenotifications.impl.NotificationFeatureModule
import ru.health.featurestatistics.StatisticsFeatureModule
import ru.health.liquid.LiquidFeatureModule

@Module(
    includes = [
        DashboardFeatureModule::class,
        StatisticsFeatureModule::class,
        LiquidFeatureModule::class,
        NotificationFeatureModule::class
    ]
)
internal interface TabModule {

    @Binds
    fun bindTabComponentFactory(impl: DefaultTabComponent.Factory): TabComponent.Factory
}
