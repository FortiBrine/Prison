package me.fortibrine.prison

import me.fortibrine.prison.di.component.DaggerPluginComponent
import org.bukkit.plugin.java.JavaPlugin

class PrisonPlugin: JavaPlugin() {

    override fun onEnable() {
        DaggerPluginComponent.factory()
            .create(this)
            .inject(this)
    }

}