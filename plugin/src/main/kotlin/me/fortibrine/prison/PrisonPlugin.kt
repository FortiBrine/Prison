package me.fortibrine.prison

import com.mongodb.kotlin.client.coroutine.MongoClient
import me.fortibrine.prison.command.CommandManager
import me.fortibrine.prison.di.component.DaggerPluginComponent
import me.fortibrine.prison.listener.ListenerManager
import me.fortibrine.prison.message.MessageConfig
import me.fortibrine.prison.service.AutomaticRenew
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrisonPlugin: JavaPlugin() {

    @Inject lateinit var commandManager: CommandManager
    @Inject lateinit var listenerManager: ListenerManager

    @Inject lateinit var messageConfig: MessageConfig

    @Inject lateinit var mongoClient: MongoClient

    @Inject lateinit var automaticRenew: AutomaticRenew

    override fun onEnable() {
        saveDefaultConfig()

        DaggerPluginComponent.factory()
            .create(this)
            .inject(this)

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, automaticRenew, 20L * 10, 20L * 600)
    }

    override fun onDisable() {
        mongoClient.close()
    }

}