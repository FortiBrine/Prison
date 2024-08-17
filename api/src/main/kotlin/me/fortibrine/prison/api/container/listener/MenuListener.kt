package me.fortibrine.prison.api.container.listener

import me.fortibrine.prison.api.container.menu.Menu
import me.fortibrine.prison.api.listener.AutoRegisterListener
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.plugin.java.JavaPlugin

class MenuListener(
    private val plugin: JavaPlugin
): AutoRegisterListener(plugin) {

    @EventHandler
    fun click(event: InventoryClickEvent) {
        val inventory = event.inventory
        val holder = inventory.holder

        if (holder is Menu) {
            holder.click(event)
        }
    }

}