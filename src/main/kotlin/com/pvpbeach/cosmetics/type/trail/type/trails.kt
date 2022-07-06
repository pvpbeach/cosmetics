package com.pvpbeach.cosmetics.type.trail.type

import com.pvpbeach.cosmetics.type.trail.TrailCosmeticType
import xyz.xenondevs.particle.ParticleEffect

// TODO: 7/6/2022 https://github.com/pvpbeach/Pivot/blob/main/Pivot-spigot/src/main/java/net/orbitgames/core/cosmetics/trail/TrailType.java
// add all of the types
val CRIT_TRAIL_TYPE = parseLinear("Crit", "crit_trail", ParticleEffect.CRIT)
val WATER_SPLASH_TRAIL_TYPE = parseLinear("Water Splash", "water_splash_trail", ParticleEffect.WATER_SPLASH)
val ANGRY_VILLAGER_TRAIL_TYPE = parseLinear("Crit", "angry_villager_trail", ParticleEffect.VILLAGER_ANGRY)
val HAPPY_VILLAGER_TRAIL_TYPE = parseLinear("Happy Villager", "happy_villager_trail", ParticleEffect.VILLAGER_HAPPY)
val HEART_TRAIL_TYPE = parseLinear("Heart", "heart_trail", ParticleEffect.HEART)
val FLAME_TRAIL_TYPE = parseLinear("Flame", "flame", ParticleEffect.FLAME)
val DRIP_LAVA_TRAIL_TYPE = parseLinear("Drip Lava", "drip_lava_trail", ParticleEffect.DRIP_LAVA)
val PORTAL_TRAIL_TYPE = parseLinear("Portal", "portal_trail", ParticleEffect.PORTAL)
val SNOWBALL_TRAIL_TYPE = parseLinear("Snowball", "snowball_trail", ParticleEffect.SNOWBALL)
val REDSTONE_TRAIL_TYPE = parseLinear("Redstone", "redstone_trail", ParticleEffect.REDSTONE)

fun parseLinear(name: String, id: String, effect: ParticleEffect): TrailCosmeticType
{
    return object : TrailCosmeticType()
    {
        override val name = name
        override val id = id
        override val effect = effect
    }
}