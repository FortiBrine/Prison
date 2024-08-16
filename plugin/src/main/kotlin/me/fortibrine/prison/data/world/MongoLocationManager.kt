package me.fortibrine.prison.data.world

import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.toList
import me.fortibrine.prison.api.data.world.Location
import me.fortibrine.prison.api.data.world.LocationManager
import me.fortibrine.prison.api.data.world.Position
import org.bukkit.Material
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MongoLocationManager @Inject constructor(
    private val database: MongoDatabase,
): LocationManager {

    private val collection: MongoCollection<MongoLocation> =
        database.getCollection("locations")

    override suspend fun addLocation(location: Location) {
        collection.insertOne(location as MongoLocation)
    }

    override suspend fun getLocations(): List<Location> {
        return collection
            .find()
            .toList()
    }

    override fun createLocation(
        world: String,
        position1: Position,
        position2: Position,
        level: Int,
        materials: List<Material>,
        name: String
    ): Location =
        MongoLocation (
            world = world,
            position1 = position1,
            position2 = position2,
            level = level,
            materials = materials,
            name = name
        )

}