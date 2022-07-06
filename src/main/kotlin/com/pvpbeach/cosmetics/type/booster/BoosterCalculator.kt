package com.pvpbeach.cosmetics.type.booster

import org.bukkit.entity.Player

object BoosterCalculator
{
    /**
     * Simple signed-arithmetic multiplication calculation.
     *
     * @param origin the original amount
     * @param boost  the amount to multiply [origin] by
     * @return the multiplied value
     */
    fun calculateBoost(origin: Int, boost: Int): Int
    {
        return origin.times(boost)
    }

    fun calculateBoostFor(player: Player, origin: Int): Int
    {
        return calculateBoost(origin, 2)
    }

    operator fun set(player: Player, origin: Int): Int
    {
        return calculateBoostFor(player, origin)
    }
}