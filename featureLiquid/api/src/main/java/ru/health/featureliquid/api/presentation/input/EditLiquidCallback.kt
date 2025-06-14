package ru.health.featureliquid.api.presentation.input

interface EditLiquidCallback {

    fun onLiquidEdited(editedVolume: Float) = Unit
}