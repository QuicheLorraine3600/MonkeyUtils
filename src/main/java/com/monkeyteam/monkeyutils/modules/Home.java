package com.monkeyteam.monkeyutils.modules;

import com.monkeyteam.monkeyutils.MonkeyUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home {
    public static class HomeCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location home = MonkeyUtils.getInstance().getConfig().getLocation("homes." + sender.getName());
                if (home == null) {
                    player.sendMessage("§rPas de home défini, faites §3/sethome §rpour en définir un !");
                    return true;
                }
                player.teleport(home);
            }
            return true;
        }
    }

    public static class SethomeCommand implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                MonkeyUtils.getInstance().getConfig().set("homes." + sender.getName(), player.getLocation());
                MonkeyUtils.getInstance().saveConfig();
                player.sendMessage("§3home §rdéfini, faites §3/home §rpour vous y téléporter");
            }
            return true;
        }
    }
}
