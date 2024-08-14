package me.fortibrine.prison.command

import me.fortibrine.prison.api.command.AbstractCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestCommand @Inject constructor(
    private val plugin: JavaPlugin,
): AbstractCommand("test", "/test", "Test command") {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {

        sender.sendMessage("Injected plugin: ${plugin.description.name}")

        if (sender !is Player) {
            return false
        }

        sender.sendMessage("HI player")
        return true
    }
}