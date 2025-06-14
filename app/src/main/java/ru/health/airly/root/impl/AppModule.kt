@file:Suppress("unused")
package ru.health.airly.root.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.health.airly.AirlyApp
import ru.health.airly.root.api.AppComponent
import ru.health.airly.tab.impl.TabModule
import ru.health.core.api.data.MetaDataStore
import ru.health.core.api.data.date.DateFormatter
import ru.health.core.impl.data.DefaultMetaDataStore
import ru.health.core.impl.data.date.DefaultDateFormatter
import ru.health.database.impl.AirlyDatabaseModule
import ru.health.featureachievement.impl.AchievementFeatureModule
import ru.health.featuredashboard.impl.DashboardFeatureModule
import ru.health.featureliquid.impl.LiquidFeatureModule
import ru.health.featurenotifications.impl.ApproveFeatureModule
import ru.health.featurestatistics.impl.StatisticsFeatureModule

@Module(
    includes = [
        AirlyDatabaseModule::class,
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

        @Provides
        fun provideMetaDataStore(context: Context): MetaDataStore =
            DefaultMetaDataStore(context.achievementDataStore)

        private val Context.achievementDataStore: DataStore<Preferences> by preferencesDataStore(
            name = "meta_data"
        )
    }
}
