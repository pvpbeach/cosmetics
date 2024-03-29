package com.pvpbeach.cosmetics.player

import com.pvpbeach.cosmetics.util.notAbsentMapOf
import com.pvpbeach.cosmetics.type.CosmeticService
import com.pvpbeach.cosmetics.type.CosmeticType
import org.bukkit.entity.Player
import java.util.*

class PlayerCosmeticData
{
    companion object
    {
        val cosmeticData =
            notAbsentMapOf<UUID, PlayerCosmeticData>(PlayerCosmeticData())

        operator fun set(id: UUID, data: PlayerCosmeticData)
        {
            this.cosmeticData[id] = data
        }

        operator fun get(id: UUID): PlayerCosmeticData = cosmeticData[id]
        operator fun get(player: Player): PlayerCosmeticData = this[player.uniqueId]
    }

    val selectedCosmeticData = hashMapOf<Class<out CosmeticType>, String>()

    operator fun <T : CosmeticType> get(type: Class<T>): CosmeticType?
    {
        // TODO: 7/7/2022 code is very ugly plz fix future patrick
        return selectedCosmeticData[type]?.let { current ->
            CosmeticService
                .cosmeticTypeMap[type.kotlin]!!
                .firstOrNull {
                    it.id == current
                }
        }
    }

    operator fun <T : CosmeticType> set(type: Class<T>, current: CosmeticType?)
    {
        if (current == null)
        {
            selectedCosmeticData.remove(type)
            return
        }

        selectedCosmeticData[type] = current.id
    }
}

data class PlayerCosmeticDataWrapper(
    val data: PlayerCosmeticData,
    val id: UUID
)
