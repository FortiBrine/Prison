package me.fortibrine.prison.api.utils

import me.fortibrine.prison.api.data.world.Position
import org.bukkit.Location

fun Location.toPosition() =
    Position(
        world = this.world!!.name,
        x = this.blockX,
        y = this.blockY,
        z = this.blockZ,
    )