package me.fortibrine.prison.api.data.player

import java.util.UUID

interface PlayerManager {

    suspend fun getPlayer(uid: UUID): PrisonPlayer
    suspend fun addPlayer(player: PrisonPlayer)
    suspend fun removePlayer(uid: UUID)
    suspend fun updatePlayer(uid: UUID, player: PrisonPlayer)

}