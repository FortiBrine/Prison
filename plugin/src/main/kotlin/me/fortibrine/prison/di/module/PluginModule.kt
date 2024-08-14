package me.fortibrine.prison.di.module

import dagger.Module
import dagger.Provides
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Singleton

@Module
class PluginModule {

    @Provides
    @Singleton
    fun provideConfig(plugin: JavaPlugin) = plugin.config

}