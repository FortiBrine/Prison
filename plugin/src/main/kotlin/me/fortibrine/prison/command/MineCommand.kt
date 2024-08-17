package me.fortibrine.prison.command

import me.fortibrine.prison.api.command.AbstractCommand
import me.fortibrine.prison.api.data.world.LocationManager
import me.fortibrine.prison.message.MessageConfig
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MineCommand @Inject constructor (
    private val locationManager: LocationManager,
    private val messageConfig: MessageConfig,
    private val plugin: JavaPlugin
): AbstractCommand("mine", "/mine", "Mine command") {

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        return true
    }

}