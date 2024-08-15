package me.fortibrine.prison

import com.mongodb.kotlin.client.coroutine.MongoClient
import me.fortibrine.prison.command.CommandManager
import me.fortibrine.prison.di.component.DaggerPluginComponent
import me.fortibrine.prison.listener.ListenerManager
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrisonPlugin: JavaPlugin() {

    @Inject lateinit var commandManager: CommandManager
    @Inject lateinit var listenerManager: ListenerManager

    @Inject lateinit var mongoClient: MongoClient

    override fun onEnable() {
        saveDefaultConfig()

        DaggerPluginComponent.factory()
            .create(this)
            .inject(this)
    }

    override fun onDisable() {
        mongoClient.close()
    }

}