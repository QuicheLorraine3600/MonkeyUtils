package com.monkeyteam.monkeyutils.modules;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Map;

public class Back implements CommandExecutor, Listener {

    private Map<Player, Location> deathPoints = new HashMap<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        deathPoints.put(player, player.getLocation());
    }

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (deathPoints.get(player) != null) {
                player.teleport(deathPoints.get(player));
                deathPoints.remove(player);
            } else {
                player.sendMessage("Aucun point de mort où se téléporter");
            }
        }
        return true;
    }
}