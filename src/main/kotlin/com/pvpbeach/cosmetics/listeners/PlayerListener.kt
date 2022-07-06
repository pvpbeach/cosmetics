package com.pvpbeach.cosmetics.listeners

import com.pvpbeach.cosmetics.events.PlayerKillEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

object PlayerListener : Listener
{
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