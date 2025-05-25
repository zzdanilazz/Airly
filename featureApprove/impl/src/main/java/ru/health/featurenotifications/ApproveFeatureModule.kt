@file:Suppress("unused")

package ru.health.featurenotifications

import dagger.Module
import ru.health.featurenotifications.presentation.ApprovePresentationModule

@Module(includes = [ApprovePresentationModule::class])
interface ApproveFeatureModule
