package com.pvpbeach.cosmetics.type.kill

import com.pvpbeach.cosmetics.events.PlayerKillEvent
import com.pvpbeach.cosmetics.filter.TargetFilterHandler
import com.pvpbeach.cosmetics.type.CosmeticType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent
import java.util.*

abstract class KillEffectCosmeticType : CosmeticType
{
    abstract override val name: String
    abstract override val id: String

    /**
     * Method gets called right when the [PlayerDeathEvent] is called.
     *
     * @param target the entity which died within the event
     * @param killer the player which killed the specified entity
     */
    abstract fun handleKillEffect(target: Entity, killer: Player)

    @EventHandler
    fun onKill(event: PlayerKillEvent)
    {
        val entity = event.killer
        val player = event.deathEvent.entity

        handleKillEffect(
            target = player,
            killer = entity
        )
    }
}