package com.pvpbeach.cosmetics

import com.pvpbeach.cosmetics.command.DebugCommand
import com.pvpbeach.cosmetics.listeners.PlayerListener
import com.pvpbeach.cosmetics.type.CosmeticService
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CosmeticsPlugin : JavaPlugin()
{
    override fun onEnable()
    {
        CosmeticService
        this
            .getCommand("cosmetics-debug")
            .executor = DebugCommand


        Bukkit
            .getPluginManager()
            .registerEvents(PlayerListener, this)

        CosmeticService
            .cosmeticTypeMap
            .values
            .flatten()
            .forEach {
                it.initialize()
            }
    }
}