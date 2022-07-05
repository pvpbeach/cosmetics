package com.pvpbeach.cosmetics.type.kill

import com.pvpbeach.cosmetics.type.CosmeticType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.PlayerDeathEvent
import java.util.*

abstract class KillEffectCosmeticType : CosmeticType
{
    abstract override val name: String
    abstract override val id: String
    override val players = mutableListOf<UUID>()

    /**
     * Method gets called right when the [PlayerDeathEvent] is called.
     *
     * @param target the entity which died within the event
     * @param killer the player which killed the specified entity
     */
    abstract fun handleKillEffect(target: Entity, killer: Player)

    @EventHandler
    fun onKill(event: EntityDeathEvent)
    {
        val entity = event.entity
        val player = event.entity.killer

        handleKillEffect(
            target = entity,
            killer = player
        )
    }
}