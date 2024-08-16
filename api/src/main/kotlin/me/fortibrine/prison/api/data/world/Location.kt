package me.fortibrine.prison.api.data.world

import org.bukkit.Material

interface Location {
    val world: String
    val position1: Position
    val position2: Position
    val level: Int
    val materials: List<Material>
    val name: String
}
