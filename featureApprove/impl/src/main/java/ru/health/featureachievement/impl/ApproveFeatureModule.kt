@file:Suppress("unused")

package ru.health.featureachievement.impl

import dagger.Module
import ru.health.featureachievement.impl.presentation.ApprovePresentationModule

@Module(includes = [ApprovePresentationModule::class])
interface ApproveFeatureModule
