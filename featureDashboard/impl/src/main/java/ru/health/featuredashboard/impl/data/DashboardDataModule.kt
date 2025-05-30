@file:Suppress("unused")

package ru.health.featuredashboard.impl.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.health.featuredashboard.api.data.DashboardDataStore
import ru.health.featuredashboard.api.data.DashboardLocalDataSource
import ru.health.featuredashboard.api.domain.DashboardRepository
import javax.inject.Singleton

@Module
internal interface DashboardDataModule {

    @Binds
    @Singleton
    fun bindDashboardRepository(impl: DefaultDashboardRepository): DashboardRepository

    @Binds
    @Singleton
    fun bindDashboardLocalDataSource(impl: DefaultDashboardLocalDataSource): DashboardLocalDataSource

    companion object {

        @Provides
        fun provideDashboardDataStore(context: Context): DashboardDataStore =
            DefaultDashboardDataStore(context.notificationDataStore)

        private val Context.notificationDataStore: DataStore<Preferences> by preferencesDataStore(
            name = "dashboard"
        )
    }

}