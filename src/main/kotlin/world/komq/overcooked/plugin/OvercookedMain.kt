/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.overcooked.plugin

import world.komq.overcooked.plugin.commands.OvercookedKommand.register
import world.komq.overcooked.plugin.config.OvercookedConfig.load
import world.komq.overcooked.plugin.events.OvercookedEvent
import io.github.monun.kommand.kommand
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import world.komq.overcooked.plugin.objects.OvercookedManager.event
import world.komq.overcooked.plugin.tasks.OvercookedConfigReloadTask
import java.io.File

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class OvercookedMain : JavaPlugin() {

    companion object {
        lateinit var instance: OvercookedMain
            private set
    }

    private val configFile = File(dataFolder, "config.yml")

    override fun onEnable() {
        instance = this
        event = OvercookedEvent()

        load(configFile)
        server.scheduler.runTaskTimer(this, OvercookedConfigReloadTask(), 0L, 20L)
        server.messenger.registerOutgoingPluginChannel(this, "Bungeecord")

        kommand {
            register("cook") {
                register(this)
            }
        }
    }

    override fun onDisable() {
        HandlerList.unregisterAll(event)
        server.messenger.unregisterOutgoingPluginChannel(this, "BungeeCord")
    }
}