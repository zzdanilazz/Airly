package ru.health.airly.root.impl

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.health.airly.AirlyApp
import ru.health.airly.root.api.AppComponent
import ru.health.airly.tab.impl.TabModule
import ru.health.featureapprove.ApproveFeatureModule
import ru.health.featuredashboard.DashboardFeatureModule
import ru.health.featurestatistics.StatisticsFeatureModule
import ru.health.inputliquid.InputLiquidFeatureModule

@Module(
    includes = [
        DataModule::class,
        DomainModule::class,
        TabModule::class,
        ApproveFeatureModule::class,
        DashboardFeatureModule::class,
        InputLiquidFeatureModule::class,
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
