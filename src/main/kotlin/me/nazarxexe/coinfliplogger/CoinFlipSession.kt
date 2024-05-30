package me.nazarxexe.coinfliplogger

import org.bukkit.OfflinePlayer
import java.math.BigDecimal

interface CoinFlipSession {
    fun founder(): OfflinePlayer
    fun amount(): BigDecimal
    fun currency(): String

    fun participator(): OfflinePlayer?
    fun winner(): OfflinePlayer?
    fun isEnded(): Boolean
}