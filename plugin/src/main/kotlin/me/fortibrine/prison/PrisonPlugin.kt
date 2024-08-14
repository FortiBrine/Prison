package me.fortibrine.prison

import me.fortibrine.prison.command.Commands
import me.fortibrine.prison.di.component.DaggerPluginComponent
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrisonPlugin: JavaPlugin() {

    @Inject lateinit var commands: Commands

    override fun onEnable() {
        DaggerPluginComponent.factory()
            .create(this)
            .inject(this)
    }

}