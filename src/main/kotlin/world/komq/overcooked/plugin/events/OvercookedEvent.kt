/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.overcooked.plugin.events

import world.komq.overcooked.plugin.objects.OvercookedManager.plugin
import world.komq.overcooked.plugin.objects.OvercookedManager.server
import io.github.monun.tap.effect.playFirework
import net.kyori.adventure.text.Component.text
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class OvercookedEvent : Listener {
    @EventHandler
    fun onPlayerQuit(e: PlayerQuitEvent) {
        val p = e.player
        // 1명이 되면 게임 종료
    }

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {

    }

    @EventHandler
    fun onPlayerDamage(e: EntityDamageEvent) {
        if(e.entity is Player) {
            (e.entity as Player).health = 0.0
        }
        e.isCancelled = true
    }
}