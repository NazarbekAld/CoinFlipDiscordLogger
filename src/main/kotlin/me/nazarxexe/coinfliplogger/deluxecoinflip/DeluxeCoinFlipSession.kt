package me.nazarxexe.coinfliplogger.deluxecoinflip

import me.nazarxexe.coinfliplogger.CoinFlipSession
import net.zithium.deluxecoinflip.game.CoinflipGame
import org.bukkit.OfflinePlayer
import java.math.BigDecimal
import java.util.*

class DeluxeCoinFlipSession(val adapt: CoinflipGame, var participator: OfflinePlayer? = null, var winner: OfflinePlayer? = null): CoinFlipSession {

    override fun founder(): OfflinePlayer {
        return adapt.offlinePlayer
    }
    override fun participator(): OfflinePlayer? {
        return participator
    }

    override fun amount(): BigDecimal {
        return BigDecimal(adapt.amount)
    }

    override fun winner(): OfflinePlayer? {
        return winner
    }

    override fun isEnded(): Boolean {
        return winner != null
    }

    override fun currency(): String {
        return adapt.provider
    }
}