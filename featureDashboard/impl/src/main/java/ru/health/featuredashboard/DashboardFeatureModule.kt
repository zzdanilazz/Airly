@file:Suppress("unused")

package ru.health.featuredashboard

import dagger.Module
import ru.health.featuredashboard.data.DashboardDataModule
import ru.health.featuredashboard.domain.DashboardDomainModule
import ru.health.featuredashboard.presentation.DashboardPresentationModule

@Module(includes = [DashboardPresentationModule::class, DashboardDataModule::class, DashboardDomainModule::class])
interface DashboardFeatureModule
