package com.pvpbeach.cosmetics.type.kill.type

import com.pvpbeach.cosmetics.particles.ParticleHandler
import com.pvpbeach.cosmetics.particles.WrappedParticle
import com.pvpbeach.cosmetics.type.kill.KillEffectCosmeticType
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.particle.ParticleEffect
import xyz.xenondevs.particle.data.texture.ItemTexture

val BLOOD_KILL_EFFECT = parseEffectLinear("Blood", "blood_effect") {
    val location = it.location
    val particles = mutableListOf<WrappedParticle>()

    for (i in 0..6)
    {
        particles += WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.REDSTONE)
            )
        )

        particles += WrappedParticle(
            effect = ParticleEffect.BLOCK_DUST,
            location = location,
            data = ItemTexture(
                ItemStack(Material.REDSTONE_BLOCK)
            )
        )
    }

    ParticleHandler.sendWrappedParticles(particles, it)
}

fun parseEffectLinear(name: String, id: String, action: (Entity) -> Unit): KillEffectCosmeticType
{
    return parseEffect(name, id) { entity, _ ->
        action.invoke(entity)
    }
}

fun parseEffect(name: String, id: String, action: (Entity, Player) -> Unit): KillEffectCosmeticType
{
    return object : KillEffectCosmeticType()
    {
        override val name = name
        override val id = id

        /**
         * Method gets called right when the [PlayerDeathEvent] is called.
         *
         * @param target the entity which died within the event
         * @param killer the player which killed the specified entity
         */
        override fun handleKillEffect(target: Entity, killer: Player)
        {
            action.invoke(target, killer)
        }
    }
}