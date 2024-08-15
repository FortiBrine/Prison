package me.fortibrine.prison.api.acceptor

import me.fortibrine.prison.api.data.player.PrisonPlayer
import org.bukkit.entity.Player

interface PlayerAcceptor {

    fun accept(player: Player, prisonPlayer: PrisonPlayer)

}
