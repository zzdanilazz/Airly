@file:Suppress("unused")

package ru.health.featurenotifications.impl

import dagger.Module
import ru.health.featurenotifications.impl.presentation.ApprovePresentationModule

@Module(includes = [ApprovePresentationModule::class])
interface ApproveFeatureModule
