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

val BLOOD_KILL_EFFECT = parseEffectLinear("Blood", "blood_kill_effect") {
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

val CHESS_KILL_EFFECT = parseEffectLinear("Chess", "chess_kill_effect") {
    val location = it.location
    val particles = listOf(
        WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.QUARTZ_BLOCK)
            )
        ),
        WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.COAL_BLOCK)
            )
        )
    )

    ParticleHandler.sendWrappedParticles(particles, it)
}

val CLOUD_KILL_EFFECT = parseEffectLinear("Cloud", "cloud_kill_effect") {
    val location = it.location

    ParticleHandler.sendWrappedParticles(
        listOf(
            WrappedParticle(
                effect = ParticleEffect.CLOUD,
                location = location
            )
        ),
        it
    )
}

val COAL_KILL_EFFECT = parseEffectLinear("Coal", "coal_kill_effect") {
    val location = it.location
    val particles = listOf(
        WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.COAL)
            )
        ),
        WrappedParticle(
            effect = ParticleEffect.SMOKE_LARGE,
            location = location,
        )
    )

    ParticleHandler.sendWrappedParticles(particles, it)
}

/**
 * Parse effect from provided parameters.
 *
 * This method calls the other method, [parseEffect] but
 * disregards the Player parameter, and just ignores it fully.
 *
 * @param name the name used to display the effect
 * @param id the string used to identify the effect, for things such as storing data regarding it.
 * @param action the action called whenever the effect has to be used, it simply creates the effect.
 *               As mentioned in the second paragraph, this action only takes Entity as parameter,
 *               and fully disregards the usual Player parameter.
 */
fun parseEffectLinear(name: String, id: String, action: (Entity) -> Unit): KillEffectCosmeticType
{
    return parseEffect(name, id) { entity, _ ->
        action.invoke(entity)
    }
}

/**
 * Parse effect from provided parameters.
 *
 * @param name the name used to display the effect
 * @param id the string used to identify the effect, for things such as storing data regarding it.
 * @param action the action called whenever the effect has to be used, it simply creates the effect.
 *               This method takes both Entity and Player as argument, and is the base method for the other
 *               method which disregards the specified Player argument.
 */
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