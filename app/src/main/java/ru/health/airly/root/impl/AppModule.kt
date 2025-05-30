package ru.health.airly.root.impl

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.health.airly.AirlyApp
import ru.health.airly.root.api.AppComponent
import ru.health.airly.tab.impl.TabModule
import ru.health.core.api.data.date.DateFormatter
import ru.health.core.impl.data.date.DefaultDateFormatter
import ru.health.featuredashboard.impl.DashboardFeatureModule
import ru.health.featurenotifications.impl.AchievementFeatureModule
import ru.health.featurenotifications.impl.ApproveFeatureModule
import ru.health.featurestatistics.impl.StatisticsFeatureModule
import ru.health.featureliquid.impl.LiquidFeatureModule

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

    @Binds
    fun dateFormatter(dateFormatter: DefaultDateFormatter): DateFormatter

    companion object {

        @Provides
        fun provideContext(application: AirlyApp): Context = application.applicationContext
    }
}
