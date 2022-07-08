package com.pvpbeach.cosmetics.menu

import com.pvpbeach.cosmetics.player.PlayerCosmeticData
import com.pvpbeach.cosmetics.type.CosmeticService
import com.pvpbeach.cosmetics.type.CosmeticType
import com.pvpbeach.cosmetics.util.LineWrapping
import io.github.nosequel.menu.Menu
import io.github.nosequel.menu.buttons.Button
import io.github.nosequel.menu.pagination.PaginatedMenu
import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class IndividualCosmeticMenu(player: Player, val cosmetic: CosmeticType) : PaginatedMenu(player, "", 36)
{
    init
    {
        this.fillerType = ItemStack(
            Material.STAINED_GLASS_PANE, 1, DyeColor
                .BLUE
                .data.toShort()
        )
    }

    /**
     * The method to get the buttons for the current inventory tick
     *
     * Use `this.buttons[index] = Button` to assign
     * a button to a slot.
     */
    override fun tick()
    {
        for (i in 0 until size)
        {
            this.buttons[i] = Button(this.fillerType)
                .setDisplayName("")
                .setClickAction {
                    it.isCancelled = true
                }
        }

        val clazz = cosmetic.javaClass.superclass as Class<out CosmeticType>
        val matches = CosmeticService.cosmeticTypeMap[
                clazz.kotlin // nv6 diff
        ]

        var index = -1

        matches?.forEach {
            buttons[++index] = IndividualCosmeticButton(
                player, it, this
            )
        }
    }
}

class IndividualCosmeticButton(val player: Player, val cosmetic: CosmeticType, menu: Menu) : Button(cosmetic.childIcon)
{
    init
    {
        val clazz = cosmetic::class.java.superclass as Class<out CosmeticType>
        val selected =
            PlayerCosmeticData[player][clazz] == cosmetic

        val canUse = CosmeticService.canUseCosmetic(player, cosmetic)
        var footerText = mutableListOf<String>()

        if (canUse)
        {
            footerText.addAll(
                listOf(
                    "",
                    "${ChatColor.GREEN}Click to select ${ChatColor.WHITE}${cosmetic.name}"
                )
            )
        }

        if (selected)
        {
            footerText = mutableListOf(
                "",
                "${ChatColor.GOLD}Click to de-select."
            )
        }

        this.displayName = "${ChatColor.GREEN}${cosmetic.name}"
        this.lore = arrayOf(
            "",
            *cosmetic.childDescription
                .map {
                    "${ChatColor.GRAY}$it"
                }.toTypedArray(),
            *footerText.toTypedArray()
        )

        this.setClickAction {
            it.isCancelled = true

            if (!canUse)
            {
                return@setClickAction
            }

            if (selected)
            {
                PlayerCosmeticData[player][clazz] = null
            } else
            {
                PlayerCosmeticData[player][clazz] = cosmetic
                menu.updateMenu()
            }
        }
    }

    override fun toItemStack(): ItemStack
    {
        val clazz = cosmetic::class.java.superclass as Class<out CosmeticType>

        val canUse = CosmeticService.canUseCosmetic(player, cosmetic)
        val selected =
            PlayerCosmeticData[player][clazz] == cosmetic

        var item = super.toItemStack()
        val meta = item.itemMeta

        if (!canUse)
        {
            item = ItemStack(Material.INK_SACK, 1, DyeColor.GRAY.dyeData.toShort())
        }

        if (selected)
        {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 10)
        }

        return item.apply {
            this.itemMeta = meta
        }
    }
}