package me.fortibrine.prison.command

import me.fortibrine.prison.api.command.AbstractCommand
import me.fortibrine.prison.api.container.menu.Menu
import org.bukkit.Material
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

        if (sender !is Player) {
            sender.sendMessage("HI console!")
            return false
        }

        Menu
            .newBuilder()
            .title("Test menu")
            .rows(3)
            .button(10, Material.MINECART) {
                it.sendMessage("you clicked minecart")
            }
            .button(13, Material.IRON_BLOCK) {
                it.sendMessage("you clicked on iron block")
            }
            .button(15, Material.GOLD_BLOCK) {
                it.sendMessage("you clicked on gold block")
            }
            .build()
            .open(sender)

        return true
    }
}