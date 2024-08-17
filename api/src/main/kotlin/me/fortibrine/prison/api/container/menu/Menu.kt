package me.fortibrine.prison.api.container.menu

import me.fortibrine.prison.api.container.button.Button
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

open class Menu private constructor (
    val title: String = "",
    val rows: Int = 3,
    val items: Map<Int, Button> = mapOf()
): InventoryHolder {

    private val inventory = Bukkit.createInventory(this, rows * 9, title)

    init {
        items.forEach { (key, button) ->
            inventory.setItem(key, button.item)
        }
    }

    override fun getInventory() = inventory

    fun click(event: InventoryClickEvent) {
        event.isCancelled = true

        val slot = event.slot
        val button = items[slot]
        val player = event.whoClicked as Player

        button?.click(player)
    }

    fun open(player: Player) {
        player.openInventory(inventory)
    }

    companion object {
        fun newBuilder() = Builder()
    }

    class Builder {
        private var title: String = ""
        private var rows: Int = 3
        private var items: MutableMap<Int, Button> = mutableMapOf()

        fun title(title: String): Builder {
            this.title = title
            return this
        }

        fun rows(rows: Int): Builder {
            this.rows = rows
            return this
        }

        fun button(
            slot: Int,
            type: Material,
            onClick: (Player) -> Unit
        ): Builder {
            items[slot] = Button(
                item = ItemStack(type),
                onClick = onClick
            )
            return this
        }

        fun button(
            slot: Int,
            title: String,
            type: Material,
            onClick: (Player) -> Unit
        ): Builder {
            items[slot] = Button(
                item = ItemStack(type).apply {
                    itemMeta?.setDisplayName(title)
                    itemMeta = itemMeta
                },
                onClick = onClick
            )
            return this
        }

        fun button(
            slot: Int,
            button: Button
        ): Builder {
            items[slot] = button

            return this
        }

        fun button(
            slot: Int,
            itemStack: ItemStack,
            onClick: (Player) -> Unit
        ): Builder {
            items[slot] = Button(
                item = itemStack,
                onClick = onClick
            )
            return this
        }

        fun build(): Menu {
            return Menu(
                title = title,
                rows = rows,
                items = items
            )
        }

    }

}
