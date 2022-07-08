package com.pvpbeach.cosmetics.menu

import com.pvpbeach.cosmetics.type.CosmeticService
import com.pvpbeach.cosmetics.type.CosmeticType
import com.pvpbeach.cosmetics.type.kill.KillEffectCosmeticType
import com.pvpbeach.cosmetics.type.message.KillMessageCosmeticType
import com.pvpbeach.cosmetics.type.trail.TrailCosmeticType
import com.pvpbeach.cosmetics.util.LineWrapping
import io.github.nosequel.menu.Menu
import io.github.nosequel.menu.buttons.Button
import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CategoryMenu(player: Player) : Menu(player, "", 45)
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

        this.buttons[20] = CategoryButton(
            CosmeticService.getModelType<KillMessageCosmeticType>()!!, player
        )

        this.buttons[22] = CategoryButton(
            CosmeticService.getModelType<KillEffectCosmeticType>()!!, player
        )

        this.buttons[24] = CategoryButton(
            CosmeticService.getModelType<TrailCosmeticType>()!!, player
        )
    }
}

class CategoryButton(type: CosmeticType, player: Player) : Button(type.parentIcon)
{
    init
    {
        val total = CosmeticService
            .cosmeticTypeMap[(type::class.java.superclass as Class<out CosmeticType>).kotlin] // this line is just beautiful

        val owned = total?.count {
            CosmeticService.canUseCosmetic(player, it)
        } ?: 0

        val ownedPercentage = owned / (total?.size ?: 0) * 100

        this.displayName = "${ChatColor.GREEN}${type.parentName}"
        this.lore = arrayOf(
            "",
            *LineWrapping
                .wrapText(type.parentDescription, 18)
                .map { "${ChatColor.GRAY}$it" }
                .toTypedArray(),
            "",
            "${ChatColor.GRAY}Owned: ${ChatColor.WHITE}${owned}/${total?.size ?: 0} ${ChatColor.DARK_GRAY}(${ownedPercentage}%)",
            "",
            "${ChatColor.YELLOW}Click to view category"
        )
        this.setClickAction {
            it.isCancelled = true // killing myself this took 5 hours or something idek

            player.closeInventory()
            IndividualCosmeticMenu(player, type).updateMenu()
        }
    }
}