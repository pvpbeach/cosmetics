package com.pvpbeach.cosmetics.type.trail.type

import com.pvpbeach.cosmetics.type.trail.TrailCosmeticType
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.particle.ParticleEffect

// TODO: 7/6/2022 https://github.com/pvpbeach/Pivot/blob/main/Pivot-spigot/src/main/java/net/orbitgames/core/cosmetics/trail/TrailType.java
// add all of the types
val CRIT_TRAIL_TYPE = parseLinear("Crit", "crit_trail", Material.DIAMOND_SWORD, ParticleEffect.CRIT)

val WATER_SPLASH_TRAIL_TYPE =
    parseLinear("Water Splash", "water_splash_trail", Material.WATER_BUCKET, ParticleEffect.WATER_SPLASH)

val ANGRY_VILLAGER_TRAIL_TYPE =
    parseLinear("Angry Villager", "angry_villager_trail", Material.LAVA_BUCKET, ParticleEffect.VILLAGER_ANGRY)

val HAPPY_VILLAGER_TRAIL_TYPE =
    parseLinear("Happy Villager", "happy_villager_trail", Material.EMERALD, ParticleEffect.VILLAGER_HAPPY)

val HEART_TRAIL_TYPE = parseLinear("Heart", "heart_trail", Material.GOLDEN_APPLE, ParticleEffect.HEART)
val FLAME_TRAIL_TYPE = parseLinear("Flame", "flame", Material.FLINT_AND_STEEL, ParticleEffect.FLAME)
val DRIP_LAVA_TRAIL_TYPE = parseLinear("Drip Lava", "drip_lava_trail", Material.LAVA_BUCKET, ParticleEffect.DRIP_LAVA)
val PORTAL_TRAIL_TYPE = parseLinear("Portal", "portal_trail", Material.ENDER_PORTAL_FRAME, ParticleEffect.PORTAL)
val SNOWBALL_TRAIL_TYPE = parseLinear("Snowball", "snowball_trail", Material.SNOW_BALL, ParticleEffect.SNOWBALL)
val REDSTONE_TRAIL_TYPE = parseLinear("Redstone", "redstone_trail", Material.REDSTONE, ParticleEffect.REDSTONE)

fun parseLinear(name: String, id: String, icon: Material, effect: ParticleEffect): TrailCosmeticType
{
    return object : TrailCosmeticType()
    {
        override val name = name
        override val id = id
        override val effect = effect
        override val childIcon = ItemStack(icon)
        override val childDescription = arrayOf<String>()
    }
}