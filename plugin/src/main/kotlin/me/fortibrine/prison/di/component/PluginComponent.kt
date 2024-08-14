package me.fortibrine.prison.di.component

import dagger.BindsInstance
import dagger.Component
import me.fortibrine.prison.PrisonPlugin
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface PluginComponent {
    fun inject(plugin: PrisonPlugin)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance plugin: PrisonPlugin): PluginComponent
    }
}
