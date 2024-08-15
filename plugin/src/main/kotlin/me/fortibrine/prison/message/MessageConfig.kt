package me.fortibrine.prison.message

import net.md_5.bungee.api.ChatColor
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageConfig @Inject constructor (
    private val plugin: JavaPlugin,
) {

    private val configFile: File = File(plugin.dataFolder, "messages.yml")
    lateinit var config: YamlConfiguration

    init {
        if (!configFile.exists()) {
            plugin.saveResource("messages.yml", false)
        }

        config = YamlConfiguration.loadConfiguration(configFile)
    }

    fun reload() {
        config = YamlConfiguration.loadConfiguration(configFile)
    }

    fun getMessage(message: MessageType): String? =
        ChatColor.translateAlternateColorCodes(
            '&', config.getString(message.path)
        )

    fun sendMessage(player: Player, message: MessageType) {
        player.sendMessage(getMessage(message))
    }

}