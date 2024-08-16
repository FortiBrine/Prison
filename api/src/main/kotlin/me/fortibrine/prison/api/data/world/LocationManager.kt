package me.fortibrine.prison.api.data.world

import org.bukkit.Material

interface LocationManager {

    suspend fun addLocation(location: Location)
    suspend fun getLocations(): List<Location>
    fun createLocation (
        world: String,
        position1: Position,
        position2: Position,
        level: Int,
        materials: List<Material>,
        name: String
    ): Location

}
