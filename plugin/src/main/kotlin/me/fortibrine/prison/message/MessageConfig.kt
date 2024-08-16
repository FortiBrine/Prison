package me.fortibrine.prison.message

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStreamReader
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

    fun getMessage(messageType: MessageType): String {
        val inputStream = plugin.getResource("messages.yml")!!
        val defaultConfiguration = YamlConfiguration.loadConfiguration(
            InputStreamReader(inputStream)
        )

        val defaultMessage = ChatColor.translateAlternateColorCodes(
            '&', defaultConfiguration.getString(messageType.path)
        )

        return ChatColor.translateAlternateColorCodes(
            '&', config.getString(messageType.path, defaultMessage)
        )

    }

    fun sendMessage(player: CommandSender, message: MessageType) {
        player.sendMessage(getMessage(message))
    }

}