package com.pvpbeach.cosmetics.listeners

import com.pvpbeach.cosmetics.events.PlayerKillEvent
import com.pvpbeach.cosmetics.util.mommify
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent

object PlayerListener : Listener
{
//    @EventHandler(priority = EventPriority.LOWEST)
//    fun onMessage(event: AsyncPlayerChatEvent)
//    {
//        event.message = event.message.mommify
//    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent)
    {
        val player = event.entity
        val killer = player.killer ?: return

        Bukkit
            .getPluginManager()
            .callEvent(
                PlayerKillEvent(killer, event)
            )
    }
}