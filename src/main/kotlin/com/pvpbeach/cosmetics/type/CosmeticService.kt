package com.pvpbeach.cosmetics.type

import com.pvpbeach.cosmetics.type.kill.KillEffectCosmeticType
import com.pvpbeach.cosmetics.type.kill.type.BLOOD_KILL_EFFECT
import kotlin.reflect.KClass

object CosmeticService
{
    val particleMap = hashMapOf<KClass<out CosmeticType>, List<CosmeticType>>(
        KillEffectCosmeticType::class to listOf(
            BLOOD_KILL_EFFECT
        )
    )
}