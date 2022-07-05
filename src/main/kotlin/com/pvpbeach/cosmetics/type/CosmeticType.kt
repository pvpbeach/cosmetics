package com.pvpbeach.cosmetics.type

import io.github.devrawr.events.Events
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerEvent
import java.util.UUID

interface CosmeticType
{
    val players: MutableList<UUID>
    val name: String
    val id: String

    fun initialize()
    {
        val methods = this.javaClass.declaredMethods
            .filter {
                it.isAnnotationPresent(EventHandler::class.java)
            }

        for (method in methods)
        {
            val event = method.parameterTypes[0]

            if (!event.isAssignableFrom(PlayerEvent::class.java))
            {
                continue
            }

            Events
                .listenTo(event.asSubclass(PlayerEvent::class.java))
                .filter {
                    players.contains(it.player.uniqueId)
                }
                .on {
                    method.invoke(this, it)
                }
        }
    }

    fun apply(player: Player)
    {
        players.add(player.uniqueId)
    }
}