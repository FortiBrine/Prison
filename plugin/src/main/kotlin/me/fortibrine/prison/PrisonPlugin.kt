package me.fortibrine.prison

import me.fortibrine.prison.command.CommandManager
import me.fortibrine.prison.di.component.DaggerPluginComponent
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrisonPlugin: JavaPlugin() {

    @Inject lateinit var commandManager: CommandManager

    override fun onEnable() {
        DaggerPluginComponent.factory()
            .create(this)
            .inject(this)
    }

}