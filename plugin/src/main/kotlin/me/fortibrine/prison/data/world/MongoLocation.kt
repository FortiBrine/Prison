package me.fortibrine.prison.data.world

import me.fortibrine.prison.api.data.world.Location
import me.fortibrine.prison.api.data.world.Position
import org.bson.codecs.pojo.annotations.BsonId
import org.bukkit.Material

data class MongoLocation (
    @BsonId
    override val world: String,
    override val position1: Position,
    override val position2: Position,
    override val level: Int,
    override val materials: List<Material>,
    override val name: String
) : Location