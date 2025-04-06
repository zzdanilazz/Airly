package ru.health.airly.di

import dagger.BindsInstance
import dagger.Component
import ru.health.airly.AirlyApp
import ru.health.airly.MainActivity
import ru.health.airly.root.impl.AppModule
import ru.health.airly.root.impl.DefaultAppComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface MainDaggerComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: AirlyApp): Builder

        fun build(): MainDaggerComponent
    }

    fun inject(app: AirlyApp)

    fun inject(activity: MainActivity)

    val rootComponentFactory: DefaultAppComponent.Factory

}
