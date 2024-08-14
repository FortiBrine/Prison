package me.fortibrine.prison.di.component

import dagger.BindsInstance
import dagger.Component
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Singleton

@Singleton
@Component(modules = [])
interface PluginComponent {
    fun inject(plugin: JavaPlugin)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance plugin: JavaPlugin): PluginComponent
    }
}
