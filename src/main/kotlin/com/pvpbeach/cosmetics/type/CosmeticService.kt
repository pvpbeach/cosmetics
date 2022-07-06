package com.pvpbeach.cosmetics.type

import com.pvpbeach.cosmetics.type.kill.KillEffectCosmeticType
import com.pvpbeach.cosmetics.type.kill.type.*
import com.pvpbeach.cosmetics.type.message.KillMessageCosmeticType
import com.pvpbeach.cosmetics.type.message.type.*
import org.bukkit.Bukkit
import kotlin.reflect.KClass

object CosmeticService
{
    val cosmeticTypeMap = hashMapOf<KClass<out CosmeticType>, MutableList<CosmeticType>>(
        KillEffectCosmeticType::class to mutableListOf(
            BLOOD_KILL_EFFECT,
            CHESS_KILL_EFFECT,
            CLOUD_KILL_EFFECT,
            COAL_KILL_EFFECT,
            COOKIE_KILL_EFFECT,
            DIAMOND_KILL_EFFECT,
            FLAME_KILL_EFFECT,
            EXPLOSION_KILL_EFFECT,
            GOLD_KILL_EFFECT,
            EMERALD_KILL_EFFECT
        ),
        KillMessageCosmeticType::class to mutableListOf(
            COMPUTER_NERD_KILL_MESSAGE,
            MEME_KILL_MESSAGE,
            PVP_KILL_MESSAGE,
            ADVANCED_KILL_MESSAGE,
            BBQ_KILL_MESSAGE,
            HONORABLE_KILL_MESSAGE,
            WOOF_WOOF_KILL_MESSAGE
        )
    )

    val dependMap = hashMapOf<String, HashMap<KClass<out CosmeticType>, MutableList<CosmeticType>>>(
        "ProtocolLib" to hashMapOf(
            KillEffectCosmeticType::class to mutableListOf(
                FIREWORK_KILL_EFFECT
            )
        )
    )

    init
    {
        registerDependEffects()
    }

    private fun registerDependEffects()
    {
        for (depend in dependMap)
        {
            val plugin = depend.key
            val effects = depend.value

            if (Bukkit.getPluginManager().getPlugin(plugin) == null)
            {
                continue
            }

            for (effect in effects)
            {
                cosmeticTypeMap.putIfAbsent(
                    effect.key, mutableListOf()
                )
                cosmeticTypeMap[effect.key]!!.addAll(effect.value)
            }
        }
    }
}