package me.fortibrine.prison.api.version

import org.bukkit.Bukkit

class MinecraftVersion (
    val version: String,
    private val numberVersion: Int
) {

    fun isLegacy() = numberVersion < 13

    companion object {
        fun getVersion(): MinecraftVersion {
            val version = Bukkit.getServer()
                .javaClass
                .`package`
                .name
                .split(".")[3]

            val numberVersion = version.split("_")[1].toInt()

            return MinecraftVersion(version, numberVersion)
        }
    }

}