package com.pvpbeach.cosmetics.particles.impl

import com.pvpbeach.cosmetics.particles.ParticleFilter
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object NormalParticleFilter : ParticleFilter
{
    override fun filter(player: Player): List<Player>
    {
        return Bukkit
            .getOnlinePlayers()
            .filter {
                it.canSee(player)
            }
    }
}