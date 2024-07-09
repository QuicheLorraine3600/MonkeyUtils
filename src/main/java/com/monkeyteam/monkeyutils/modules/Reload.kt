package com.monkeyteam.monkeyutils.modules

import com.monkeyteam.monkeyutils.MonkeyUtils.Companion.instance
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object Reload: CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        sender.sendMessage("Reloading config...")
        instance.reloadConfig()
        sender.sendMessage("Config reloaded")
        return true
    }
}