package com.pvpbeach.cosmetics.particles

import com.pvpbeach.cosmetics.filter.TargetFilter
import com.pvpbeach.cosmetics.filter.TargetFilterHandler
import com.pvpbeach.cosmetics.filter.impl.NormalTargetFilter
import org.bukkit.Location
import org.bukkit.entity.Entity
import xyz.xenondevs.particle.ParticleBuilder
import xyz.xenondevs.particle.ParticleEffect
import xyz.xenondevs.particle.data.ParticleData
import java.awt.Color

data class WrappedParticle(
    val effect: ParticleEffect,
    val location: Location,
    val color: Color? = null,
    val amount: Int = 2,
    val data: ParticleData? = null
)

object ParticleHandler
{

    fun sendWrappedParticles(particles: List<WrappedParticle>, target: Entity)
    {
        for (particle in particles)
        {
            val builder =
                ParticleBuilder(particle.effect, particle.location)
                    .setAmount(particle.amount)

            if (particle.color != null)
            {
                builder.setColor(particle.color)
            }

            if (particle.data != null)
            {
                builder.setParticleData(particle.data)
            }

            processPlayerParticle(target, builder)
        }
    }

    fun processPlayerParticle(player: Entity, builder: ParticleBuilder)
    {
        val filtered = TargetFilterHandler
            .filter
            .filter(player)

        builder.display {
            filtered.contains(it)
        }
    }
}