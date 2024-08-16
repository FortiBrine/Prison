package me.fortibrine.prison.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.fortibrine.prison.api.data.world.Location
import me.fortibrine.prison.api.data.world.LocationManager
import me.fortibrine.prison.message.MessageConfig
import me.fortibrine.prison.message.MessageType
import org.bukkit.Bukkit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max
import kotlin.math.min

@Singleton
class AutomaticRenew @Inject constructor(
    private val locationManager: LocationManager,
    private val messageConfig: MessageConfig
): Runnable {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val locations: MutableList<Location> = mutableListOf()

    init {
        load()
    }

    fun load() {
        scope.launch {
            locations.clear()
            locations.addAll(locationManager.getLocations())
        }
    }

    override fun run() {

        locations.forEach { location ->
            val x1 = min(location.position1.x, location.position2.x)
            val x2 = max(location.position1.x, location.position2.x)
            val y1 = min(location.position1.y, location.position2.y)
            val y2 = max(location.position1.y, location.position2.y)
            val z1 = min(location.position1.z, location.position2.z)
            val z2 = max(location.position1.z, location.position2.z)

            for (x in x1..x2) {
                for (y in y1..y2) {
                    for (z in z1..z2) {
                        val material = location.materials.random()
                        Bukkit.getWorld(location.world)?.getBlockAt(x, y, z)?.type = material
                    }
                }
            }
        }

        Bukkit.getOnlinePlayers().forEach { player ->
            messageConfig.sendMessage(player, MessageType.MINE_UPDATE)
        }
    }

}