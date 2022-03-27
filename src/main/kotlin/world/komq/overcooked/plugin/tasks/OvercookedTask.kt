/*
 * Copyright (c) 2022 BaeHyeonWoo
 *
 *  Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

package world.komq.overcooked.plugin.tasks

import world.komq.overcooked.plugin.objects.OvercookedManager.plugin

/***
 * @author BaeHyeonWoo
 *
 * "Until my feet are crushed,"
 * "Until I can get ahead of myself."
 */

class OvercookedTask : Runnable {
    override fun run() {
        plugin.logger.info("Hello World!")
    }
}