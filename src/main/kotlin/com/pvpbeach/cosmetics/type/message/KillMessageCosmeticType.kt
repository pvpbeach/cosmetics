package com.pvpbeach.cosmetics.type.message

import com.pvpbeach.cosmetics.events.PlayerKillEvent
import com.pvpbeach.cosmetics.filter.TargetFilterHandler
import com.pvpbeach.cosmetics.type.CosmeticType
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.inventory.ItemStack
import java.util.*

abstract class KillMessageCosmeticType(override val name: String, override val id: String) : CosmeticType
{
    abstract val killMessages: Array<KillMessage>

    override val parentDescription =
        "Lorem ipsum mommy???"

    override val parentName = "Kill Messages"
    override val parentIcon = ItemStack(
        Material.NAME_TAG
    )

    @EventHandler
    fun onKill(event: PlayerKillEvent)
    {
        val killer = event.killer
        val entity = event.deathEvent.entity

        val random = killMessages.random()
        val filter = TargetFilterHandler.filter

        event.deathEvent.deathMessage = null

        val names = when (random.pattern)
        {
            KillMessagePattern.KILLER_TARGET -> Pair(killer.name, entity.name)
            KillMessagePattern.TARGET_KILLER -> Pair(entity.name, killer.name)
        }

        filter
            .filter(entity)
            .forEach {
                it.sendMessage(
                    "${ChatColor.RED}${names.first} ${ChatColor.YELLOW}was ${random.message} by ${ChatColor.GREEN}${names.second}"
                )
            }
    }
}

data class KillMessage(
    val message: String,
    val pattern: KillMessagePattern
)
{
    companion object
    {
        operator fun set(message: String, pattern: KillMessagePattern): KillMessage
        {
            return KillMessage(
                message,
                pattern
            )
        }
    }
}

enum class KillMessagePattern
{
    KILLER_TARGET,
    TARGET_KILLER
}
