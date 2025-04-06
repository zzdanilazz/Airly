package ru.health.airly

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import ru.health.airly.di.DaggerMainDaggerComponent
import ru.health.airly.di.MainDaggerComponent
import ru.health.core.AppLifecycleObserver

class AirlyApp : Application() {

    internal lateinit var mainDaggerComponent: MainDaggerComponent

    override fun onCreate() {
        super.onCreate()
        mainDaggerComponent = DaggerMainDaggerComponent
            .builder()
            .application(this)
            .build()

        mainDaggerComponent.inject(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(AppLifecycleObserver())
    }
}
