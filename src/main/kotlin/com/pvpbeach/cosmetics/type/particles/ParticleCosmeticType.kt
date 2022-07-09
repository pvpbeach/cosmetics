package com.pvpbeach.cosmetics.type.particles

import com.pvpbeach.cosmetics.player.PlayerCosmeticData
import com.pvpbeach.cosmetics.type.CosmeticType
import io.github.devrawr.tasks.Tasks
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

abstract class ParticleCosmeticType(tickDelay: Long) : CosmeticType
{
    override val parentDescription =
        "Lorem ipsum mommy"

    override val parentName = "Trails"
    override val parentIcon = ItemStack(
        Material.BONE
    )

    init
    {
        Tasks
            .sync()
            .repeating(0L, tickDelay) {
                Bukkit.getOnlinePlayers()
                    .filter { PlayerCosmeticData[it].selectedCosmeticData[this::class.java.superclass as Class<out CosmeticType>] == this.id }
                    .forEach {
                        tick(it)
                    }
            }
    }

    abstract fun tick(player: Player)
}