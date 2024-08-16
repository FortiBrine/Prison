package me.fortibrine.prison.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.fortibrine.prison.api.data.world.LocationManager
import org.bukkit.Bukkit
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.max
import kotlin.math.min

@Singleton
class AllowBreakLocationBlocks @Inject constructor(
    private val locationManager: LocationManager,
    private val plugin: JavaPlugin
) {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun load() {
        scope.launch {
            locationManager.getLocations().forEach { location ->
                val x1 = min(location.position1.x, location.position2.x)
                val x2 = max(location.position1.x, location.position2.x)
                val y1 = min(location.position1.y, location.position2.y)
                val y2 = max(location.position1.y, location.position2.y)
                val z1 = min(location.position1.z, location.position2.z)
                val z2 = max(location.position1.z, location.position2.z)

                for (x in x1..x2) {
                    for (y in y1..y2) {
                        for (z in z1..z2) {
                            Bukkit.getScheduler().runTask(
                                plugin, Runnable {
                                    val block = Bukkit.getWorld(location.world)?.getBlockAt(x, y, z)
                                    block?.setMetadata("location", FixedMetadataValue(plugin, location.world))
                                }
                            )

                        }
                    }
                }
            }
        }
    }

}