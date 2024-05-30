package me.nazarxexe.coinfliplogger.deluxecoinflip

import me.nazarxexe.coinfliplogger.CoinFlipLogWebHook
import me.nazarxexe.coinfliplogger.CoinFlipSession
import net.zithium.deluxecoinflip.api.events.CoinflipCompletedEvent
import net.zithium.deluxecoinflip.api.events.CoinflipCreatedEvent
import net.zithium.deluxecoinflip.game.CoinflipGame
import org.bukkit.OfflinePlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import java.math.BigDecimal

class CoinFlipListener: Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onCompleted(e: CoinflipCompletedEvent) {
        CoinFlipLogWebHook.handle(object : CoinFlipSession {
            override fun founder(): OfflinePlayer {
                return e.winner // Let's just assume the founder is the winner because plugin doesn't provide session.
            }

            override fun amount(): BigDecimal {
                return BigDecimal(e.winnings)
            }

            override fun currency(): String {
                return "(Money)" // Let's just assume its money because plugin doesn't provide any even clone of the session. And no way to get what exactly player won.
            }

            override fun participator(): OfflinePlayer? {
                return e.loser
            }

            override fun winner(): OfflinePlayer? {
                return e.winner
            }

            override fun isEnded(): Boolean {
                return true
            }
        })
    }
    @EventHandler(priority = EventPriority.HIGH)
    fun onCreated(e: CoinflipCreatedEvent) {
        if (e.isCancelled) return
        CoinFlipLogWebHook.handle(DeluxeCoinFlipSession(e.coinflipGame))
    }
}