package com.pvpbeach.cosmetics.player.sync

import com.pvpbeach.cosmetics.player.PlayerCosmeticData
import com.pvpbeach.cosmetics.player.PlayerCosmeticDataWrapper
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType
import io.github.nosequel.data.sync.PubSubType

object PlayerCosmeticDataSync
{
    lateinit var subscriber: PubSubType<PlayerCosmeticDataWrapper>

    fun sync()
    {
        subscriber = DataHandler.createPubSubType(DataStoreType.REDIS) {
            PlayerCosmeticData[it.id] = it.data
        }
    }
}