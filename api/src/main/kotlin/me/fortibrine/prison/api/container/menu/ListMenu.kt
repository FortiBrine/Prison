package me.fortibrine.prison.api.container.menu

import me.fortibrine.prison.api.container.button.Button
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ListMenu(
    private val title: String,
    private val rows: Int,
    private val buttons: List<Button>,
    private val nextPage: ItemStack,
    private val previousPage: ItemStack,
) {

    private val pages: MutableList<Menu> = mutableListOf()

    init {
        val itemsIsPage = 7 * rows - 14
        val chunks = buttons.chunked(itemsIsPage)

        chunks.forEachIndexed { pageNumber, chunk ->

            val pageBuilder = Menu.newBuilder()
                .title(title)
                .rows(rows)

            val alignRow = 1
            val alignColumn = 1

            chunk.chunked(7).forEachIndexed { indexRow, buttons ->
                buttons.forEachIndexed { indexColumn, button ->
                    pageBuilder.button(alignRow * (indexRow + 1) * 9 + alignColumn + indexColumn, button)
                }
            }

            pageBuilder.button(rows * 9 - 9, previousPage) {
                if (pageNumber == 0) {
                    return@button
                }

                pages[pageNumber - 1].open(it)
            }

            pageBuilder.button(rows * 9 - 1, nextPage) {
                if (pageNumber == pages.lastIndex) {
                    return@button
                }

                pages[pageNumber + 1].open(it)
            }

            val page = pageBuilder.build()

            pages.add(page)
        }

    }

    fun open(player: Player) {
        pages.firstOrNull()?.open(player)
    }

}