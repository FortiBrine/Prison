package me.fortibrine.prison.command

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.fortibrine.prison.api.command.AbstractCommand
import me.fortibrine.prison.data.player.MongoPlayerManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LevelCommand @Inject constructor(
    private val playerManager: MongoPlayerManager,
): AbstractCommand("data", "/data", "Data command") {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) {
            sender.sendMessage("You ain't player")
            return true
        }

        scope.launch {
            val uid = sender.uniqueId
            val prisonPlayer = playerManager.getPlayer(uid)

            sender.sendMessage("Your data: $prisonPlayer")
        }

        return true
    }

}