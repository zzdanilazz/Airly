@file:Suppress("unused")

package ru.health.featureapprove

import dagger.Module
import ru.health.featureapprove.presentation.ApprovePresentationModule

@Module(includes = [ApprovePresentationModule::class])
interface ApproveFeatureModule
