package com.pvpbeach.cosmetics.type

import com.pvpbeach.cosmetics.player.PlayerCosmeticData
import io.github.devrawr.events.Events
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.inventory.ItemStack

interface CosmeticType
{
    val name: String
    val id: String

    val parentIcon: ItemStack
    val childIcon: ItemStack

    val parentName: String

    val parentDescription: String
    val childDescription: Array<String>

    fun getCosmeticType(): Class<out CosmeticType>
    {
        var superClass = this.javaClass.superclass

        while (superClass.superclass != CosmeticType::class.java)
        {
            superClass = superClass.superclass
        }

        return superClass as Class<out CosmeticType>
    }

    fun initialize()
    {
        val methods = this.javaClass
            .superclass
            .declaredMethods
            .filter {
                it.isAnnotationPresent(EventHandler::class.java)
            }

        for (method in methods)
        {
            val event = method.parameterTypes[0]
            var retrievePlayer: ((Event) -> Player?)? = null

            if (!Event::class.java.isAssignableFrom(event))
            {
                continue
            }

            if (PlayerEvent::class.java.isAssignableFrom(event))
            {
                retrievePlayer = {
                    (it as PlayerEvent).player
                }
            } else if (ProjectileLaunchEvent::class.java.isAssignableFrom(event))
            {
                retrievePlayer = {
                    val entity = (it as ProjectileLaunchEvent)
                        .entity
                        .shooter

                    if (entity !is Player)
                    {
                        null
                    } else
                    {
                        entity
                    }
                }
            }

            if (retrievePlayer != null)
            {
                Events
                    .listenTo(event.asSubclass(Event::class.java))
                    .filter {
                        val player = retrievePlayer(it) ?: return@filter false
                        val profile = PlayerCosmeticData[player]

                        var superclass = this::class.java

                        while (CosmeticType::class.java.isAssignableFrom(superclass.superclass) && superclass.superclass != CosmeticType::class.java)
                        {
                            superclass = superclass.superclass as Class<out CosmeticType>
                        }

                        profile[superclass] == this
                    }
                    .on {
                        method.invoke(this, it)
                    }
            }

            continue
        }
    }
}