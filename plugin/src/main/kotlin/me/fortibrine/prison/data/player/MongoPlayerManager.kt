package me.fortibrine.prison.data.player

import com.mongodb.client.model.Filters
import com.mongodb.client.model.ReplaceOptions
import com.mongodb.kotlin.client.coroutine.MongoCollection
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.firstOrNull
import me.fortibrine.prison.api.data.player.PlayerManager
import me.fortibrine.prison.api.data.player.PrisonPlayer
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MongoPlayerManager @Inject constructor (
    private val database: MongoDatabase,
): PlayerManager {

    private val collection: MongoCollection<MongoPrisonPlayer> =
        database.getCollection("players")

    override suspend fun getPlayer(uid: UUID): PrisonPlayer {
//        val query = Filters.eq(MongoPrisonPlayer::uid.name, uid)
        val query = Filters.eq("_id", uid)

        return collection
            .find<MongoPrisonPlayer>(query)
            .firstOrNull() ?: run {
                val player = MongoPrisonPlayer(uid)
                addPlayer(player)
                return player
            }
    }

    override suspend fun addPlayer(player: PrisonPlayer) {
        collection.insertOne(player as MongoPrisonPlayer)
    }

    override suspend fun removePlayer(uid: UUID) {
        val query = Filters.eq("_id", uid)

        collection.deleteOne(query)
    }

    override suspend fun updatePlayer(uid: UUID, player: PrisonPlayer) {
        val query = Filters.eq("_id", uid)
        val options = ReplaceOptions().upsert(true)
        collection.replaceOne(query, player as MongoPrisonPlayer, options)
    }

}