@file:Suppress("unused")

package ru.health.featurenotifications.impl.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.health.featurenotifications.api.data.NotificationDataStore
import ru.health.featurenotifications.api.domain.NotificationRepository
import javax.inject.Singleton

@Module
internal interface NotificationDataModule {

    @Binds
    @Singleton
    fun bindNotificationRepository(
        impl: DefaultNotificationRepository
    ): NotificationRepository

    companion object {

        @Provides
        fun provideNotificationDataStore(context: Context): NotificationDataStore =
            DefaultNotificationDataStore(context.notificationDataStore)

        private val Context.notificationDataStore: DataStore<Preferences> by preferencesDataStore(
            name = "notifications"
        )
    }
}
