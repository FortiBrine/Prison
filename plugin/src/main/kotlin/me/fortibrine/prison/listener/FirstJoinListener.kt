package me.fortibrine.prison.listener

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.fortibrine.prison.api.acceptor.PlayerAcceptor
import me.fortibrine.prison.api.data.player.PlayerManager
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirstJoinListener @Inject constructor(
    private val playerAcceptor: PlayerAcceptor,
    private val playerManager: PlayerManager,
    private val plugin: JavaPlugin
): Listener {

    init {
        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @EventHandler
    fun onFirstJoin(event: PlayerJoinEvent) {
        val player = event.player
        if (player.hasPlayedBefore()) return

        scope.launch {
            val prisonPlayer = playerManager.getPlayer(player.uniqueId)

            Bukkit.getScheduler().runTask(
                plugin, Runnable {
                    playerAcceptor.accept(player, prisonPlayer)
                }
            )
        }

    }

}