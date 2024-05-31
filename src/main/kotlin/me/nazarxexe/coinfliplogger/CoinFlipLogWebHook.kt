package me.nazarxexe.coinfliplogger

import club.minnced.discord.webhook.WebhookClient
import club.minnced.discord.webhook.WebhookClientBuilder
import club.minnced.discord.webhook.send.WebhookEmbed
import club.minnced.discord.webhook.send.WebhookEmbedBuilder
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import java.math.MathContext
import java.math.RoundingMode
import java.util.function.Function

object CoinFlipLogWebHook {

    var webhook: WebhookClient? = null
    var plugin: CoinFlipLog? = null

    var coinFlipPlace: Function<CoinFlipSession, WebhookEmbed> = Function {
        return@Function WebhookEmbedBuilder()
            .setTitle(WebhookEmbed.EmbedTitle("${it.founder().name} created an coinflip.", null))
            .setDescription("The bet is ${it.amount().round(MathContext(4, RoundingMode.CEILING))}${it.currency()}.")
            .build()
    }
    var coinFlipEnd: Function<CoinFlipSession, WebhookEmbed> = Function {
        return@Function WebhookEmbedBuilder()
            .setTitle(WebhookEmbed.EmbedTitle("${it.participator()?.name} and ${it.founder().name} started the coin flip.", null))
            .setDescription("The winner is ${it.winner()?.name} and won ${it.amount().round(MathContext(4, RoundingMode.CEILING))}${it.currency()}!")
            .build()
    }

    fun initializeWebHook(url: String) {
        webhook = WebhookClientBuilder(url)
            .setThreadFactory {
                val thread = Thread(it)
                thread.name = "DiscordCoinflipWebhook"
                thread.isDaemon = true
                return@setThreadFactory thread
            }
            .build()
    }

    fun handle(session: CoinFlipSession) {
        val webhook = webhook ?: return
        if (session.isEnded()) {
            if (plugin != null) {
                // Use async
                object : BukkitRunnable() {
                    override fun run() {
                        webhook.send(coinFlipEnd.apply(session)).get()
                    }
                }.runTaskAsynchronously(plugin as Plugin)
                return
            }
            // Use sync
            webhook.send(coinFlipEnd.apply(session)).get()
            return
        }
        if (plugin != null) {
            // Use async
            object : BukkitRunnable() {
                override fun run() {
                    webhook.send(coinFlipPlace.apply(session)).get()
                }
            }.runTaskAsynchronously(plugin as Plugin)
            return
        }
        // Use sync
        webhook.send(coinFlipPlace.apply(session)).get()
    }
}