package com.pvpbeach.cosmetics.type.kill.type

import com.pvpbeach.cosmetics.type.kill.KillEffectCosmeticType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

class BloodKillEffect : KillEffectCosmeticType()
{
    /**
     * Method gets called right when the [PlayerDeathEvent] is called.
     *
     * @param target the entity which died within the event
     * @param killer the player which killed the specified entity
     */
    override fun handleKillEffect(target: Entity, killer: Player)
    {

    }
}