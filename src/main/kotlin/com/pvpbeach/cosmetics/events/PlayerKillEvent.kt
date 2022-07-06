package com.pvpbeach.cosmetics.events

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerEvent

class PlayerKillEvent(player: Player, val deathEvent: PlayerDeathEvent) : PlayerEvent(player)
{
    companion object
    {
        private val HANDLERS =
            HandlerList()

        @JvmStatic
        fun getHandlerList() =
            HANDLERS
    }

    val killer = player

    override fun getHandlers() =
        HANDLERS
}