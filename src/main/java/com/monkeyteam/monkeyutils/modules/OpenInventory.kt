package com.monkeyteam.monkeyutils.modules

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object OpenInventory : CommandExecutor {
  override fun onCommand(
      sender: CommandSender,
      cmd: Command,
      label: String,
      args: Array<out String>
  ): Boolean {
    if (sender is Player) {
      if (args.isNotEmpty()) {
        val target = Bukkit.getPlayer(args[0])
        if (target != null) {
          sender.openInventory(target.inventory)
        }
      }
    }
    return true
  }
}
