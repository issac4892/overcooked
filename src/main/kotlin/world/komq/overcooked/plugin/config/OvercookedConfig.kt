/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.overcooked.plugin.config

import io.github.monun.tap.config.Config
import io.github.monun.tap.config.ConfigSupport
import java.io.File

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

object OvercookedConfig {
    @Config
    var allowAdminToPlay = false

    @Config
    var lobbyserver = "server"

    fun load(configFile: File) {
        ConfigSupport.compute(this, configFile)
    }
}