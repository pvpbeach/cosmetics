package com.pvpbeach.cosmetics.filter

import org.bukkit.entity.Entity
import org.bukkit.entity.Player

interface TargetFilter
{
    fun filter(player: Entity): List<Player>
}