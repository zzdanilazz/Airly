@file:Suppress("unused")

package ru.health.featuredashboard.impl

import dagger.Module
import ru.health.featuredashboard.impl.data.DashboardDataModule
import ru.health.featuredashboard.impl.domain.DashboardDomainModule
import ru.health.featuredashboard.impl.presentation.root.DashboardPresentationModule

@Module(includes = [DashboardPresentationModule::class, DashboardDataModule::class, DashboardDomainModule::class])
interface DashboardFeatureModule
