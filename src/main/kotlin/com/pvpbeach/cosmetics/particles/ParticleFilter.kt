package com.pvpbeach.cosmetics.particles

import org.bukkit.entity.Entity
import org.bukkit.entity.Player

interface ParticleFilter
{
    fun filter(player: Entity): List<Player>
}