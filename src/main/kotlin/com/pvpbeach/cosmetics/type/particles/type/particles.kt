package com.pvpbeach.cosmetics.type.particles.type

import com.pvpbeach.cosmetics.particles.ParticleHandler
import com.pvpbeach.cosmetics.particles.WrappedParticle
import com.pvpbeach.cosmetics.type.particles.ParticleCosmeticType
import com.pvpbeach.cosmetics.util.notAbsentMapOf
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.particle.ParticleEffect
import java.util.UUID
import kotlin.math.cos
import kotlin.math.sin

val WATER_DROP_PARTICLES =
    parseLinearSpiral("water_drop_particles", "Water Drops", Material.WATER_BUCKET, ParticleEffect.DRIP_WATER)

fun parseLinearSpiral(
    id: String,
    name: String,
    icon: Material,
    effect: ParticleEffect
): ParticleCosmeticType
{
    return object : ParticleCosmeticType(2)
    {
        val tickMap = notAbsentMapOf<UUID, Int>(0)
        val ticks = 40

        override val childDescription = arrayOf<String>()

        override val name = name
        override val id = id
        override val childIcon = ItemStack(
            icon
        )

        override fun tick(player: Player)
        {
            var current = tickMap[player.uniqueId]

            if (current >= ticks)
                current = -1

            tickMap[player.uniqueId] = current + 1

            val angle = current.toDouble() * 0.15707963267948966
            val location = player
                .location
                .clone()
                .add(0.1, 0.0, 0.1)


            val cos = cos(angle)
            val sin = sin(angle)

            val list = mutableListOf<Location>(
                location.clone().add(1 * cos, 0.5 + 1.0 * cos, 1.0 * sin),
                location.clone().add(1 * cos, 1.5 + 1.0 * cos, 1.0 * sin),
                location.clone().add(1 * cos, 1.5 + 1.0 * cos, 1.0 * sin)
            )

            val particles = list
                .map {
                    WrappedParticle(
                        effect = effect,
                        location = it,
                        speed = 1F
                    )
                }

            ParticleHandler.sendWrappedParticles(particles, player)
        }
    }
}