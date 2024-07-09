package com.monkeyteam.monkeyutils.modules;

import com.monkeyteam.monkeyutils.MonkeyUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.List;

public class Whitelist implements Listener, CommandExecutor {

    @EventHandler
    public void onPlayerConnect(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (!MonkeyUtils.getInstance().getConfig().getStringList("whitelist").contains(player.getName().toLowerCase())) {
            event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "Pas dans la whitelist !");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("add")) {
                List<String> whitelist = MonkeyUtils.getInstance().getConfig().getStringList("whitelist");
                if (!whitelist.contains(args[1].toLowerCase())) {
                   whitelist.add(args[1].toLowerCase());
                   MonkeyUtils.getInstance().getConfig().set("whitelist", whitelist);
                   MonkeyUtils.getInstance().saveConfig();
                   sender.sendMessage("Joueur ajouté");
                   return true;
                }
                sender.sendMessage("Joueur déjà ajouté");
                return true;
            }
            if (args[0].equalsIgnoreCase("remove")) {
                List<String> whitelist = MonkeyUtils.getInstance().getConfig().getStringList("whitelist");
                if (whitelist.contains(args[1].toLowerCase())) {
                    whitelist.remove(args[1].toLowerCase());
                    MonkeyUtils.getInstance().getConfig().set("whitelist", whitelist);
                    MonkeyUtils.getInstance().saveConfig();
                    sender.sendMessage("Joueur retiré");
                    return true;
                }
                sender.sendMessage("Joueur déjà retiré");
                return true;
            }
        }

        sender.sendMessage("Whitelist:");
        MonkeyUtils.getInstance().getConfig().getStringList("whitelist").forEach(sender::sendMessage);
        return true;
    }

}
