package com.pvpbeach.cosmetics.type.kill.type

import com.pvpbeach.cosmetics.particles.ParticleHandler
import com.pvpbeach.cosmetics.particles.WrappedParticle
import com.pvpbeach.cosmetics.type.kill.KillEffectCosmeticType
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.particle.ParticleEffect
import xyz.xenondevs.particle.data.texture.ItemTexture
import java.awt.Color

val BLOOD_KILL_EFFECT = parseEffectLinear("Blood", "blood_effect") {
    val location = it.location
    val world = location.world

    val particles = listOf<WrappedParticle>(
        WrappedParticle(
            effect = ParticleEffect.REDSTONE,
            location = location,
            color = Color.RED
        ),
        WrappedParticle(
            effect = ParticleEffect.BLOCK_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.REDSTONE_BLOCK)
            )
        )
    )

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