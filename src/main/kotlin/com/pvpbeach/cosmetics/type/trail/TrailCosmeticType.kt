package com.pvpbeach.cosmetics.type.trail

import com.pvpbeach.cosmetics.particles.ParticleHandler
import com.pvpbeach.cosmetics.particles.WrappedParticle
import com.pvpbeach.cosmetics.type.CosmeticType
import io.github.devrawr.tasks.Task
import io.github.devrawr.tasks.Tasks
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.Potion
import xyz.xenondevs.particle.ParticleEffect

abstract class TrailCosmeticType : CosmeticType
{
    abstract val effect: ParticleEffect
    override val parentDescription =
        "Lorem ipsum mommy"

    override val parentName = "Trails"
    override val parentIcon = ItemStack(
        Material.NETHER_STAR
    )

    @EventHandler
    fun onShoot(event: ProjectileLaunchEvent)
    {
        val projectile = event.entity
        val player = event.entity.shooter

        if (player is Player)
        {
            val predicate = {
                projectile == null || projectile.isOnGround || projectile.isDead || kotlin.run {
                    if (projectile is Potion)
                    {
                        return@run !projectile.isValid
                    }

                    false
                }
            }

            val task = Tasks
                .sync()
                .repeating(0L, 2L) {
                    ParticleHandler
                        .sendWrappedParticles(
                            particles = listOf(
                                WrappedParticle(
                                    effect = this.effect,
                                    location = projectile.location,
                                    amount = 2
                                )
                            ),
                            target = player
                        )
                }
                .cancelIf(predicate())

            var cancelTask: Task? = null

            cancelTask = Tasks
                .sync()
                .repeating(0L, 3L) {
                    if (predicate())
                    {
                        task.cancel()
                        cancelTask!!.cancel()
                    }
                }
        }
    }
}