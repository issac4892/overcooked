/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.overcooked.plugin.commands

import io.github.monun.kommand.getValue
import io.github.monun.kommand.node.LiteralNode
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import world.komq.overcooked.plugin.objects.OvercookedManager.isRunning
import world.komq.overcooked.plugin.objects.OvercookedManager.playable
import world.komq.overcooked.plugin.objects.OvercookedManager.plugin
import world.komq.overcooked.plugin.objects.OvercookedManager.server
import world.komq.overcooked.plugin.objects.OvercookedManager.startGame
import world.komq.overcooked.plugin.objects.OvercookedManager.stopGame

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object OvercookedKommand {
    fun register(builder: LiteralNode) {
        builder.apply {
            requires { isOp }
            then("start") {
                executes {
                    if (!isRunning) {
                        startGame()
                        if (playable) {
                            server.broadcast(text("Game Started."))
                        }
                    }
                    else {
                        sender.sendMessage(text("The game is not running.", NamedTextColor.RED))
                    }
                }
            }
            then("stop") {
                executes {
                    if (isRunning) {
                        stopGame()
                        server.broadcast(text("Game Stopped."))
                    }
                    else {
                        sender.sendMessage(text("The game is not running.", NamedTextColor.RED))
                    }
                }
            }
            then("settings") {
                then("allowAdminsToPlay") {
                    then("option" to bool()) {
                        executes {
                            val option: Boolean by it
                            if (option) {
                                if (!isRunning) {
                                    plugin.config.set("allow-admins-to-play", true)
                                    plugin.saveConfig()
                                    sender.sendMessage(text("Allowing admins to play this game."))
                                }
                                else {
                                    sender.sendMessage(text("The game is still running! Please stop the game in order to change this configuration!", NamedTextColor.RED))
                                }
                            }
                            else {
                                if (!isRunning) {
                                    plugin.config.set("allow-admins-to-play", false)
                                    plugin.saveConfig()
                                    sender.sendMessage(text("Disallowing admins to play this game."))
                                }
                                else {
                                    sender.sendMessage(text("The game is still running! Please stop the game in order to change this configuration!", NamedTextColor.RED))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}