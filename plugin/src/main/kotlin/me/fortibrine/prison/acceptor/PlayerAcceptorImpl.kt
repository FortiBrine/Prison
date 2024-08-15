package me.fortibrine.prison.acceptor

import me.fortibrine.prison.api.acceptor.PlayerAcceptor
import me.fortibrine.prison.api.data.player.PrisonPlayer
import me.fortibrine.prison.api.version.MinecraftVersion
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerAcceptorImpl @Inject constructor(

): PlayerAcceptor {

    override fun accept(player: Player, prisonPlayer: PrisonPlayer) {
        player.sendTitle(
            "Привет",
            "Мы очень рады видеть вас на сервере"
        )

        val item = ItemStack(
            Material.matchMaterial("WOOD_PICKAXE") ?: Material.WOODEN_PICKAXE
        ).apply {
            itemMeta!!.isUnbreakable = true
            itemMeta = itemMeta
        }

        player.inventory.addItem(item)
    }

}