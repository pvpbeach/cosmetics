package com.pvpbeach.cosmetics.menu

import com.pvpbeach.cosmetics.type.CosmeticType
import io.github.nosequel.menu.Menu
import io.github.nosequel.menu.buttons.Button
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

class CategoryMenu(player: Player) : Menu(player, "", 54)
{
    /**
     * The method to get the buttons for the current inventory tick
     *
     *
     * Use `this.buttons[index] = Button` to assign
     * a button to a slot.
     */
    override fun tick()
    {
        this.buttons[27] = Button(Material.SKULL_ITEM)
            .setDisplayName("")
            .setClickAction {

            }
        TODO("Not yet implemented")
    }
}

class CategoryButton(type: CosmeticType, material: Material) : Button(material)
{
    init
    {
        this.displayName = "${ChatColor.GREEN}${type.name}"
        this.lore = arrayOf(
            *type.description
                .map { "${ChatColor.GRAY}$it" }
                .toTypedArray()
        )
    }
}