package com.monkeyteam.monkeyutils.modules;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CropsBreak implements Listener {

    @EventHandler
    public void onBreakCrop(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL) {
            try {
                if (e.getClickedBlock().getType() == Material.FARMLAND) {
                    e.setCancelled(true);
                }
            } catch (NullPointerException ignored) {
            }
        }
    }
}
