package ru.health.airly.root.impl

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.health.airly.AirlyApp
import ru.health.airly.root.api.AppComponent
import ru.health.airly.tab.impl.TabModule
import ru.health.featurenotifications.AchievementFeatureModule
import ru.health.featurenotifications.ApproveFeatureModule
import ru.health.featuredashboard.DashboardFeatureModule
import ru.health.featurestatistics.StatisticsFeatureModule
import ru.health.liquid.LiquidFeatureModule

@Module(
    includes = [
        DataModule::class,
        DomainModule::class,
        TabModule::class,
        ApproveFeatureModule::class,
        DashboardFeatureModule::class,
        LiquidFeatureModule::class,
        AchievementFeatureModule::class,
        StatisticsFeatureModule::class
    ]
)
interface AppModule {

    @Binds
    fun bindAppRootComponentFactory(impl: DefaultAppComponent.Factory): AppComponent.Factory

    companion object {

        @Provides
        fun provideContext(application: AirlyApp): Context = application.applicationContext
    }
}
