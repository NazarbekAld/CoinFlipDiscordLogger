import be.seeseemelk.mockbukkit.MockBukkit
import me.nazarxexe.coinfliplogger.CoinFlipLogWebHook
import me.nazarxexe.coinfliplogger.CoinFlipSession
import org.bukkit.OfflinePlayer
import java.math.BigDecimal
import kotlin.test.Test

class WebhookTest {

    //@Test
    fun test() {
        val server = MockBukkit.mock()
        val p = server.addPlayer("Kliner")
        CoinFlipLogWebHook.initializeWebHook("https://discord.com/api/webhooks/")
        CoinFlipLogWebHook.handle(object : CoinFlipSession {
            override fun founder(): OfflinePlayer {
                return p
            }

            override fun amount(): BigDecimal {
                return BigDecimal(69)
            }

            override fun currency(): String {
                return "$"
            }

            override fun participator(): OfflinePlayer? {
                return null
            }

            override fun winner(): OfflinePlayer? {
                return null
            }

            override fun isEnded(): Boolean {
                return false
            }
        })
        val p2 = server.addPlayer("Freeman")
        CoinFlipLogWebHook.handle(object : CoinFlipSession {
            override fun founder(): OfflinePlayer {
                return p
            }

            override fun amount(): BigDecimal {
                return BigDecimal(420)
            }

            override fun currency(): String {
                return "$"
            }

            override fun participator(): OfflinePlayer? {
                return p2
            }

            override fun winner(): OfflinePlayer? {
                return p2
            }

            override fun isEnded(): Boolean {
                return true
            }
        })
        MockBukkit.unmock()
    }

}