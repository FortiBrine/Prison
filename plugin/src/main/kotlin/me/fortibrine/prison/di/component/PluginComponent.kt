package me.fortibrine.prison.di.component

import dagger.BindsInstance
import dagger.Component
import me.fortibrine.prison.PrisonPlugin
import me.fortibrine.prison.di.module.ApiModule
import me.fortibrine.prison.di.module.BindsModule
import me.fortibrine.prison.di.module.MongoModule
import me.fortibrine.prison.di.module.PluginModule
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        PluginModule::class,
        MongoModule::class,
        BindsModule::class,
        ApiModule::class
    ]
)
interface PluginComponent {
    fun inject(plugin: PrisonPlugin)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance plugin: JavaPlugin): PluginComponent
    }
}
