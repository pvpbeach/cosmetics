package com.pvpbeach.cosmetics.type.message.type

import com.pvpbeach.cosmetics.type.message.KillMessage
import com.pvpbeach.cosmetics.type.message.KillMessageCosmeticType
import com.pvpbeach.cosmetics.type.message.KillMessagePattern
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

// TODO: 7/6/2022 https://github.com/pvpbeach/Pivot/blob/main/Pivot-spigot/src/main/java/net/orbitgames/core/cosmetics/deathmessage/DeathMessageType.java
// add all of the messages above

val COMPUTER_NERD_KILL_MESSAGE = parseLinear(
    "Computer Nerd", "computer_nerd_kill_message", Material.COMMAND, arrayOf(
        KillMessage("ALT+F4'd", KillMessagePattern.KILLER_TARGET),
        KillMessage("404'd", KillMessagePattern.KILLER_TARGET),
        KillMessage("DELETED", KillMessagePattern.KILLER_TARGET),
        KillMessage("rm -rf /*'d", KillMessagePattern.KILLER_TARGET)
    )
)

val MEME_KILL_MESSAGE = parseLinear(
    "Memes", "memes_kill_message", Material.DIRT, arrayOf(
        KillMessage("shown the wae to", KillMessagePattern.KILLER_TARGET),
        KillMessage("oofed", KillMessagePattern.KILLER_TARGET),
        KillMessage("memed", KillMessagePattern.KILLER_TARGET)
    )
)

val PVP_KILL_MESSAGE = parseLinear(
    "PvP Pack", "pvp_kill_message", Material.DIAMOND_SWORD, arrayOf(
        KillMessage("slaughtered", KillMessagePattern.KILLER_TARGET),
        KillMessage("shut down", KillMessagePattern.KILLER_TARGET),
        KillMessage("eliminated", KillMessagePattern.KILLER_TARGET),
        KillMessage("clapped", KillMessagePattern.KILLER_TARGET),
        KillMessage("jittered", KillMessagePattern.KILLER_TARGET)
    )
)

val ADVANCED_KILL_MESSAGE = parseLinear(
    "Advanced", "advanced_kill_message", Material.COMMAND, arrayOf(
        KillMessage("finished off", KillMessagePattern.KILLER_TARGET),
        KillMessage("jumped", KillMessagePattern.KILLER_TARGET),
        KillMessage("attacked", KillMessagePattern.KILLER_TARGET),
        KillMessage("sniped", KillMessagePattern.KILLER_TARGET),
        KillMessage("destroyed", KillMessagePattern.KILLER_TARGET),
        KillMessage("demolished", KillMessagePattern.KILLER_TARGET),
        KillMessage("laid out", KillMessagePattern.KILLER_TARGET),
        KillMessage("wrecked", KillMessagePattern.KILLER_TARGET),
        KillMessage("sent to the hub", KillMessagePattern.KILLER_TARGET),
        KillMessage("taken out live", KillMessagePattern.KILLER_TARGET),
    )
)

val BBQ_KILL_MESSAGE = parseLinear(
    "BBQ", "bbq_kill_message", Material.COOKED_BEEF, arrayOf(
        KillMessage("glazed in BBQ sauce", KillMessagePattern.KILLER_TARGET),
        KillMessage("thrown chili powder at", KillMessagePattern.KILLER_TARGET),
        KillMessage("not spicy enough for", KillMessagePattern.KILLER_TARGET),
        KillMessage("slipped in BBQ sauce off the edge spilled", KillMessagePattern.KILLER_TARGET),
        KillMessage("put on a BBQ grill", KillMessagePattern.TARGET_KILLER),
    )
)

val HONORABLE_KILL_MESSAGE = parseLinear(
    "Honorable", "honorable_kill_message", Material.WATCH, arrayOf(
        KillMessage("died in close combat to", KillMessagePattern.KILLER_TARGET),
        KillMessage("fought to the edge with", KillMessagePattern.TARGET_KILLER),
        KillMessage("fell to the great marksmanship of", KillMessagePattern.TARGET_KILLER),
        KillMessage("stumbled off a ledge with the help of", KillMessagePattern.KILLER_TARGET)
    )
)

val WOOF_WOOF_KILL_MESSAGE = parseLinear(
    "Woof Woof", "woof_woof_kill_message", Material.BONE, arrayOf(
        KillMessage("bitten", KillMessagePattern.KILLER_TARGET),
        KillMessage("howled into the void", KillMessagePattern.KILLER_TARGET),
        KillMessage("caught the ball thrown", KillMessagePattern.KILLER_TARGET),
        KillMessage("distracted by a puppy placed", KillMessagePattern.KILLER_TARGET)
    )
)

private fun parseLinear(
    name: String,
    id: String,
    material: Material,
    messages: Array<KillMessage>
): KillMessageCosmeticType
{
    return object : KillMessageCosmeticType(name, id)
    {
        override val killMessages = messages
        override val childIcon = ItemStack(material)
        override val childDescription = killMessages
            .map { "${ChatColor.BLUE}• ${ChatColor.YELLOW}${it.message}" }
            .toTypedArray()
    }
}