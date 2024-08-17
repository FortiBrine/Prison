package me.fortibrine.prison.api.container

import me.fortibrine.prison.api.container.listener.MenuListener
import org.bukkit.plugin.java.JavaPlugin

class ContainerAPI (
    private val plugin: JavaPlugin
) {

    init {
        MenuListener(plugin)
    }

}
