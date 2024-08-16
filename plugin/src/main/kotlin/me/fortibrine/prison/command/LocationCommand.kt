package me.fortibrine.prison.command

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.fortibrine.prison.api.command.AbstractCommand
import me.fortibrine.prison.api.data.world.LocationManager
import me.fortibrine.prison.api.data.world.Position
import me.fortibrine.prison.message.MessageConfig
import me.fortibrine.prison.message.MessageType
import me.fortibrine.prison.service.AutomaticRenew
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationCommand @Inject constructor (
    private val locationManager: LocationManager,
    private val messageConfig: MessageConfig,
    private val plugin: JavaPlugin,
    private val automaticRenew: AutomaticRenew
): AbstractCommand("location", "/location [x1] [y1] [z1] [x2] [y2] [z2] [lvl] [materials] [name]", "Data command") {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.isOp) {
            messageConfig.sendMessage(sender, MessageType.NOT_OP)
            return true;
        }

        if (sender !is Player) {
            messageConfig.sendMessage(sender, MessageType.NOT_PLAYER)
            return true
        }

        if (args.size < 9) {
            sender.sendMessage(cmd.usage)
            return true
        }

        val world = sender.location.world!!

        val location1 = Position(
            world.name,
            args[0].toInt(),
            args[1].toInt(),
            args[2].toInt()
        )

        val location2 = Position(
            world.name,
            args[3].toInt(),
            args[4].toInt(),
            args[5].toInt()
        )

        val lvl = args[6].toInt()
        val materials = args[7]
            .uppercase()
            .split(",")
            .map { Material.matchMaterial(it) ?: return false }

        val name = args.asList().subList(8, args.size).joinToString(" ")

        val location = locationManager.createLocation(
            world.name,
            location1,
            location2,
            lvl,
            materials,
            name
        )

        scope.launch {

            locationManager.addLocation(
                location
            )

            automaticRenew.load()

            Bukkit.getScheduler().runTask(
                plugin, Runnable {
                    messageConfig.sendMessage(sender, MessageType.ADD_LOCATION)
                }
            )
        }

        return true

    }
}