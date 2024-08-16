package me.fortibrine.prison.settings

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JoinListener @Inject constructor(
    private val plugin: JavaPlugin
): Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        val player = event.player

        player.gameMode = GameMode.SURVIVAL

        val world = Bukkit.getWorld(plugin.config.getString("start.world") ?: return) ?: return
        val x = plugin.config.getInt("start.x")
        val y = plugin.config.getInt("start.y")
        val z = plugin.config.getInt("start.z")

        player.teleport(Location(world, x.toDouble(), y.toDouble(), z.toDouble()))

    }

}