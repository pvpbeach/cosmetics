package com.pvpbeach.cosmetics.type.message.type

import com.pvpbeach.cosmetics.type.message.KillMessage
import com.pvpbeach.cosmetics.type.message.KillMessageCosmeticType
import com.pvpbeach.cosmetics.type.message.KillMessagePattern

// TODO: 7/6/2022 https://github.com/pvpbeach/Pivot/blob/main/Pivot-spigot/src/main/java/net/orbitgames/core/cosmetics/deathmessage/DeathMessageType.java
// add all of the messages above

val COMPUTER_NERD_KILL_MESSAGE = parseLinear(
    "Computer Nerd", "computer_nerd_kill_message", arrayOf(
        KillMessage(
            "ALT+F4'd",
            KillMessagePattern.KILLER_TARGET
        ),
        KillMessage(
            "404'd",
            KillMessagePattern.KILLER_TARGET
        ),
        KillMessage(
            "DELETED",
            KillMessagePattern.KILLER_TARGET
        ),
        KillMessage(
            "rm -rf /*'d",
            KillMessagePattern.KILLER_TARGET
        )
    )
)

val MEME_KILL_MESSAGE = parseLinear(
    "Memes", "memes_kill_message", arrayOf(
        KillMessage(
            "shown the wae to",
            KillMessagePattern.KILLER_TARGET
        ),
        KillMessage(
            "oofed",
            KillMessagePattern.KILLER_TARGET
        ),
        KillMessage(
            "memed",
            KillMessagePattern.KILLER_TARGET
        )
    )
)

fun parseLinear(
    name: String,
    id: String,
    messages: Array<KillMessage>
): KillMessageCosmeticType
{
    return object : KillMessageCosmeticType(name, id)
    {
        override val killMessages = messages
    }
}