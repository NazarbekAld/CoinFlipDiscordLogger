package me.nazarxexe.coinfliplogger

import me.nazarxexe.coinfliplogger.deluxecoinflip.CoinFlipListener
import org.bukkit.plugin.java.JavaPlugin

class CoinFlipLog: JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(CoinFlipListener(), this)
        CoinFlipLogWebHook.plugin = this
        CoinFlipLogWebHook.initializeWebHook(config.getString("webhook") ?: error("Webhook is null."))
    }
}