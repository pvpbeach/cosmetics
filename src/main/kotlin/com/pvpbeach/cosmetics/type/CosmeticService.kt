package com.pvpbeach.cosmetics.type

import com.pvpbeach.cosmetics.type.kill.KillEffectCosmeticType
import com.pvpbeach.cosmetics.type.kill.type.BLOOD_KILL_EFFECT
import com.pvpbeach.cosmetics.type.kill.type.CHESS_KILL_EFFECT
import com.pvpbeach.cosmetics.type.kill.type.CLOUD_KILL_EFFECT
import com.pvpbeach.cosmetics.type.kill.type.COAL_KILL_EFFECT
import kotlin.reflect.KClass

object CosmeticService
{
    val particleMap = hashMapOf<KClass<out CosmeticType>, List<CosmeticType>>(
        KillEffectCosmeticType::class to listOf(
            BLOOD_KILL_EFFECT,
            CHESS_KILL_EFFECT,
            CLOUD_KILL_EFFECT,
            COAL_KILL_EFFECT
        )
    )
}