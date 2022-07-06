package com.pvpbeach.cosmetics.type

import com.pvpbeach.cosmetics.player.PlayerCosmeticData
import io.github.devrawr.events.Events
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerEvent

interface CosmeticType
{
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
                    val player = it.player
                    val profile = PlayerCosmeticData[player]

                    var superclass = this::class.java

                    while (CosmeticType::class.java.isAssignableFrom(superclass.superclass))
                    {
                        superclass = superclass.superclass as Class<out CosmeticType>
                    }

                    profile[superclass] == this
                }
                .on {
                    method.invoke(this, it)
                }
        }
    }
}