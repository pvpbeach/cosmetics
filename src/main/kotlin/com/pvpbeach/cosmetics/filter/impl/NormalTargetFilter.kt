package com.pvpbeach.cosmetics.filter.impl

import com.pvpbeach.cosmetics.filter.TargetFilter
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

object NormalTargetFilter : TargetFilter
{
    override fun filter(player: Entity): List<Player>
    {
        return Bukkit
            .getOnlinePlayers()
            .filter {
                player !is Player || it.canSee(player)
            }
    }
}