package me.fortibrine.prison.api.data.player

import java.util.UUID

interface PrisonPlayer {
    val uid: UUID
    val level: Int
    val brokenBlocks: Int
}
