package com.pvpbeach.cosmetics.type.kill.type

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.PacketContainer
import com.pvpbeach.cosmetics.particles.ParticleHandler
import com.pvpbeach.cosmetics.particles.WrappedParticle
import com.pvpbeach.cosmetics.type.kill.KillEffectCosmeticType
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.particle.ParticleEffect
import xyz.xenondevs.particle.data.texture.ItemTexture
import java.util.*

val BLOOD_KILL_EFFECT = parseEffectLinear("Blood", "blood_kill_effect") {
    val location = it.location
    val particles = mutableListOf<WrappedParticle>()

    for (i in 0..6)
    {
        particles += WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.REDSTONE)
            )
        )

        particles += WrappedParticle(
            effect = ParticleEffect.BLOCK_DUST,
            location = location,
            data = ItemTexture(
                ItemStack(Material.REDSTONE_BLOCK)
            )
        )
    }

    ParticleHandler.sendWrappedParticles(particles, it)
}

val CHESS_KILL_EFFECT = parseEffectLinear("Chess", "chess_kill_effect") {
    val location = it.location
    val particles = listOf(
        WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.QUARTZ_BLOCK)
            )
        ),
        WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.COAL_BLOCK)
            )
        )
    )

    ParticleHandler.sendWrappedParticles(particles, it)
}

val CLOUD_KILL_EFFECT = parseEffectLinear("Cloud", "cloud_kill_effect") {
    ParticleHandler.sendWrappedParticles(
        listOf(
            WrappedParticle(
                effect = ParticleEffect.CLOUD,
                location = it.location
            )
        ),
        it
    )
}

val COAL_KILL_EFFECT = parseEffectLinear("Coal", "coal_kill_effect") {
    val location = it.location
    val particles = listOf(
        WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.COAL)
            )
        ),
        WrappedParticle(
            effect = ParticleEffect.SMOKE_LARGE,
            location = location,
        )
    )

    ParticleHandler.sendWrappedParticles(particles, it)
}

val COOKIE_KILL_EFFECT = parseEffectLinear("Cookie", "cookie_kill_effect") {
    ParticleHandler.sendWrappedParticles(
        listOf(
            WrappedParticle(
                effect = ParticleEffect.ITEM_CRACK,
                location = it.location,
                data = ItemTexture(
                    ItemStack(Material.COOKIE)
                )
            )
        ),
        it
    )
}

val GOLD_KILL_EFFECT = parseEffectLinear("Gold", "gold_kill_effect") {
    val location = it.location
    val particles = mutableListOf<WrappedParticle>()

    for (i in 0..6)
    {
        particles += WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.GOLD_INGOT)
            )
        )
    }

    particles += WrappedParticle(
        effect = ParticleEffect.FIREWORKS_SPARK,
        location = location,
    )

    ParticleHandler.sendWrappedParticles(particles, it)
}

val DIAMOND_KILL_EFFECT = parseEffectLinear("Diamond", "diamond_kill_effect") {
    val location = it.location
    val particles = mutableListOf<WrappedParticle>()

    for (i in 0..6)
    {
        particles += WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.DIAMOND)
            )
        )
    }

    particles += WrappedParticle(
        effect = ParticleEffect.FIREWORKS_SPARK,
        location = location,
    )

    ParticleHandler.sendWrappedParticles(particles, it)
}

val EMERALD_KILL_EFFECT = parseEffectLinear("Emerald", "emerald_kill_effect") {
    val location = it.location
    val particles = mutableListOf<WrappedParticle>()

    for (i in 0..6)
    {
        particles += WrappedParticle(
            effect = ParticleEffect.ITEM_CRACK,
            location = location,
            data = ItemTexture(
                ItemStack(Material.EMERALD)
            )
        )
    }

    particles += WrappedParticle(
        effect = ParticleEffect.FIREWORKS_SPARK,
        location = location,
    )

    ParticleHandler.sendWrappedParticles(particles, it)
}

val EXPLOSION_KILL_EFFECT = parseEffectLinear("Explosion", "explosion_kill_effect") {
    ParticleHandler.sendWrappedParticles(
        listOf(
            WrappedParticle(
                effect = ParticleEffect.EXPLOSION_LARGE,
                location = it.location
            )
        ),
        it
    )
}

val FIREWORK_KILL_EFFECT = parseEffectLinear("Firework", "firework_kill_effect") {
    val location = it.location
    val filter = ParticleHandler.filter

    val manager = ProtocolLibrary.getProtocolManager()
    val packet = PacketContainer(PacketType.Play.Server.SPAWN_ENTITY)

    // TODO: 7/6/2022 i really don't think this will work... will most likely have to fix this sometime.
    // packet data was retrieved from this table:
    //Packet ID	State	Bound To	Field Name	Field Type	Notes
    //0x00	Play	Client	Entity ID	VarInt	A unique integer ID mostly used in the protocol to identify the entity.
    //Entity UUID	UUID	A unique identifier that is mostly used in persistence and places where the uniqueness matters more.
    //Type	VarInt	The type of the entity (see "type" field of the list of Mob types).
    //X	Double
    //Y	Double
    //Z	Double
    //Pitch	Angle	To get the real pitch, you must divide this by (256.0F / 360.0F)
    //Yaw	Angle	To get the real yaw, you must divide this by (256.0F / 360.0F)
    //Head Yaw	Angle
    //Data	VarInt	Meaning dependent on the value of the Type field, see Object Data for details.
    //Velocity X	Short	Same units as Set Entity Motion.
    //Velocity Y	Short
    //Velocity Z	Short
    // https://wiki.vg/Protocol#Spawn_Entity
    packet.integers.write(0, (Math.random() * 10000).toInt())
    packet.uuiDs.write(1, UUID.randomUUID())
    packet.integers.write(2, 25)
    packet.doubles.write(3, location.x)
    packet.doubles.write(4, location.y)
    packet.doubles.write(5, location.z)
    packet.float.write(6, location.pitch)
    packet.float.write(7, location.yaw)
    packet.float.write(8, 0.0F)
    packet.integers.write(9, 0)
    packet.shorts.write(10, 0)
    packet.shorts.write(11, 0)
    packet.shorts.write(12, 0)

    filter
        .filter(it)
        .forEach { current ->
            manager.sendServerPacket(
                current, packet
            )
        }
}

val FLAME_KILL_EFFECT = parseEffectLinear("Flame", "flame_kill_effect") {
    ParticleHandler.sendWrappedParticles(
        listOf(
            WrappedParticle(
                effect = ParticleEffect.FLAME,
                location = it.location
            )
        ),
        it
    )
}


/**
 * Parse effect from provided parameters.
 *
 * This method calls the other method, [parseEffect] but
 * disregards the Player parameter, and just ignores it fully.
 *
 * @param name the name used to display the effect
 * @param id the string used to identify the effect, for things such as storing data regarding it.
 * @param action the action called whenever the effect has to be used, it simply creates the effect.
 *               As mentioned in the second paragraph, this action only takes Entity as parameter,
 *               and fully disregards the usual Player parameter.
 */
fun parseEffectLinear(name: String, id: String, action: (Entity) -> Unit): KillEffectCosmeticType
{
    return parseEffect(name, id) { entity, _ ->
        action.invoke(entity)
    }
}

/**
 * Parse effect from provided parameters.
 *
 * @param name the name used to display the effect
 * @param id the string used to identify the effect, for things such as storing data regarding it.
 * @param action the action called whenever the effect has to be used, it simply creates the effect.
 *               This method takes both Entity and Player as argument, and is the base method for the other
 *               method which disregards the specified Player argument.
 */
fun parseEffect(name: String, id: String, action: (Entity, Player) -> Unit): KillEffectCosmeticType
{
    return object : KillEffectCosmeticType()
    {
        override val name = name
        override val id = id

        /**
         * Method gets called right when the [PlayerDeathEvent] is called.
         *
         * @param target the entity which died within the event
         * @param killer the player which killed the specified entity
         */
        override fun handleKillEffect(target: Entity, killer: Player)
        {
            action.invoke(target, killer)
        }
    }
}