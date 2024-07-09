package com.monkeyteam.monkeyutils.modules;

import com.monkeyteam.monkeyutils.MonkeyUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("Reloading config...");
        MonkeyUtils.getInstance().reloadConfig();
        commandSender.sendMessage("Config reloaded");
        return true;
    }

}
