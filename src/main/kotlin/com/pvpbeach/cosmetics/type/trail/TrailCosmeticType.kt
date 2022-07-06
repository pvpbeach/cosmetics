package com.pvpbeach.cosmetics.type.trail

import com.pvpbeach.cosmetics.particles.ParticleHandler
import com.pvpbeach.cosmetics.particles.WrappedParticle
import com.pvpbeach.cosmetics.type.CosmeticType
import io.github.devrawr.tasks.Tasks
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.ProjectileLaunchEvent
import xyz.xenondevs.particle.ParticleEffect

abstract class TrailCosmeticType : CosmeticType
{
    abstract val effect: ParticleEffect

    @EventHandler
    fun onShoot(event: ProjectileLaunchEvent)
    {
        val projectile = event.entity
        val player = event.entity.shooter

        if (player is Player)
        {
            val predicate = {
                projectile == null || projectile.isOnGround || projectile.isDead
            }

            val task = Tasks
                .sync()
                .repeating(0L, 2L) {
                    ParticleHandler
                        .sendWrappedParticles(
                            particles = listOf(
                                WrappedParticle(
                                    effect = this.effect,
                                    location = projectile.location
                                )
                            ),
                            target = player
                        )

                }
                .cancelIf(predicate())

            Tasks
                .sync()
                .repeating(0L, 3L) {
                    if (predicate())
                    {
                        task.cancel()
                    }
                }
        }
    }
}