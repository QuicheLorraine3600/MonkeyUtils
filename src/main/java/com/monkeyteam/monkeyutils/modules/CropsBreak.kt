package com.monkeyteam.monkeyutils.modules

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

object CropsBreak: Listener {
    @EventHandler
    fun onBreakCrop(e: PlayerInteractEvent) {
        if (e.action == Action.PHYSICAL) {
            try {
                if (e.clickedBlock!!.type == Material.FARMLAND) {
                    e.isCancelled = true
                }
            } catch (ignored: NullPointerException) {
            }
        }
    }
}