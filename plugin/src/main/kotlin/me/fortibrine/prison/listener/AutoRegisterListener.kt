package me.fortibrine.prison.listener

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

open class AutoRegisterListener(
    private val plugin: JavaPlugin
): Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

}