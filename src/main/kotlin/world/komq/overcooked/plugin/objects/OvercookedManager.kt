/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.overcooked.plugin.objects

import com.google.common.io.ByteStreams
import net.kyori.adventure.text.Component
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import world.komq.overcooked.plugin.OvercookedMain
import world.komq.overcooked.plugin.events.OvercookedEvent
import world.komq.overcooked.plugin.tasks.OvercookedCookingTask
import world.komq.overcooked.plugin.tasks.OvercookedRespawnTimerTask
import java.util.UUID
import kotlin.collections.HashMap

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object OvercookedManager {
    private var administrators = arrayListOf(
        "389c4c9b-6342-42fc-beb3-922a7d7a72f9", // komq
        "5082c832-7f7c-4b04-b0c7-2825062b7638", // BaeHyeonWoo
        "762dea11-9c45-4b18-95fc-a86aab3b39ee", // aroxu
        "6340af5e-bbd1-41c4-b86d-3425b347063c", // RootPi
        "63e8e8a6-4104-4abf-811b-2ed277a02738", // norhu1130
        "ad524e9e-acf5-4977-9c12-938212663361", // ssapgosuX
        "3013e38a-74a7-41d4-8e68-71ee440c0e20" // choda100x
    )

    val plugin = OvercookedMain.instance
    lateinit var event: Listener

    val server = plugin.server

    val userScore = HashMap<UUID, Int>()
    val finalScore = HashMap<UUID, Int>()
    val respawnTime = HashMap<UUID, Int>()

    var isRunning = false
    var playable = false

    fun Player.sendTo(serverName : String) {
        @Suppress("UnstableApiUsage")
        val out = ByteStreams.newDataOutput()
        out.writeUTF("Connect")
        out.writeUTF(serverName)
        sendPluginMessage(plugin, "BungeeCord", out.toByteArray())
    }

    fun startGame() {
        val playerList = ArrayList<Player>()

        server.onlinePlayers.forEach {
            if(it.uniqueId.toString() in administrators.toString()) {
                if(!plugin.config.getBoolean("allow-admins-to-play")) {
                    it.gameMode = GameMode.SPECTATOR
                    it.sendMessage(Component.text("관리자 -> GAMEMODE: SPECTATOR"))
                }
            }
        }

        val players = server.onlinePlayers.asSequence().filter {
            it.gameMode != GameMode.SPECTATOR
        }.toMutableList()

        server.scheduler.runTaskTimer(plugin, OvercookedRespawnTimerTask(), 0L, 20L)
        server.scheduler.runTaskTimer(plugin, OvercookedCookingTask(), 0L, 20L)
    }

    fun stopGame() {
        server.onlinePlayers.forEach {
            it.inventory.clear()
        }

        isRunning = false
    }
}