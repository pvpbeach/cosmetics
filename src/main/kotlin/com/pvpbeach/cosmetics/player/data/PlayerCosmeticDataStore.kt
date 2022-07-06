package com.pvpbeach.cosmetics.player.data

import com.pvpbeach.cosmetics.player.PlayerCosmeticData
import com.pvpbeach.cosmetics.player.PlayerCosmeticDataWrapper
import io.github.nosequel.data.DataHandler
import io.github.nosequel.data.DataStoreType
import io.github.nosequel.data.store.StoreType
import java.util.UUID

object PlayerCosmeticDataStore
{
    lateinit var storage: StoreType<UUID, PlayerCosmeticDataWrapper>

    // TODO: 7/6/2022 this code is very ugly uwu. find better solution.
    fun init()
    {
        storage = DataHandler
            .createStoreType<UUID, PlayerCosmeticDataWrapper>(DataStoreType.MONGO)
            .apply {
                this.retrieveAll {
                    PlayerCosmeticData[it.id] = it.data
                }
            }
    }
}