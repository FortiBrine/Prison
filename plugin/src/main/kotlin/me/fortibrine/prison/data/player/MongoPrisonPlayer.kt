package me.fortibrine.prison.data.player

import me.fortibrine.prison.api.data.player.PrisonPlayer
import org.bson.codecs.pojo.annotations.BsonId
import java.util.UUID

data class MongoPrisonPlayer (
    @BsonId
    override val uid: UUID = UUID.randomUUID(),
    override val level: Int = 1,
    override val brokenBlocks: Int = 0
): PrisonPlayer
