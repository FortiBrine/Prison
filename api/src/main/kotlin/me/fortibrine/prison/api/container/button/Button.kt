package me.fortibrine.prison.api.container.button

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class Button (
    val item: ItemStack,
    private val onClick: (Player) -> Unit = { },
) {

    fun click(player: Player) {
        onClick(player)
    }

}