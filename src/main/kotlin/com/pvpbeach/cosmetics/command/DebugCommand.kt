package com.pvpbeach.cosmetics.command

import com.pvpbeach.cosmetics.menu.CategoryMenu
import com.pvpbeach.cosmetics.player.PlayerCosmeticData
import com.pvpbeach.cosmetics.type.CosmeticService
import com.pvpbeach.cosmetics.type.CosmeticType
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object DebugCommand : CommandExecutor
{
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean
    {
        if (sender !is Player)
        {
            return true
        }

        if (args.isEmpty())
        {
            CategoryMenu(sender).updateMenu()
            return true
        }

        val id = args[0]
        val cosmetic = CosmeticService
            .cosmeticTypeMap
            .values
            .flatten()
            .firstOrNull {
                it.id == id
            }

        if (cosmetic == null)
        {
            sender.sendMessage("L bozo + ratio: $id")
            return true
        }

        PlayerCosmeticData[sender.uniqueId][cosmetic::class.java.superclass as Class<out CosmeticType>] = cosmetic
        sender.sendMessage("selected cosmetic")

        return true
    }
}