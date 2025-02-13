package com.monkeyteam.monkeyutils.modules

import com.monkeyteam.monkeyutils.MonkeyUtils
import java.util.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

object Whitelist : Listener, CommandExecutor {
  @EventHandler
  fun onPlayerConnect(event: PlayerLoginEvent) {
    val player = event.player
    if (!MonkeyUtils.instance.config
        .getStringList("whitelist")
        .contains(player.name.lowercase(Locale.getDefault()))) {
      event.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "Pas dans la whitelist !")
    }
  }

  override fun onCommand(
      sender: CommandSender,
      command: Command,
      label: String,
      args: Array<out String>
  ): Boolean {
    val whitelist: List<String> = MonkeyUtils.instance.config.getStringList("whitelist")
    if (args.size >= 2) {
      if (args[0].equals("add", ignoreCase = true)) {
        if (!whitelist.contains(args[1].lowercase(Locale.getDefault()))) {
          MonkeyUtils.instance.config.set(
              "whitelist", whitelist + args[1].lowercase(Locale.getDefault()))
          MonkeyUtils.instance.saveConfig()
          sender.sendMessage("Joueur ajouté")
          return true
        }
        sender.sendMessage("Joueur déjà ajouté")
        return true
      }
      if (args[0].equals("remove", ignoreCase = true)) {
        if (whitelist.contains(args[1].lowercase(Locale.getDefault()))) {
          MonkeyUtils.instance.config.set(
              "whitelist", whitelist - args[1].lowercase(Locale.getDefault()))
          MonkeyUtils.instance.saveConfig()
          sender.sendMessage("Joueur retiré")
          return true
        }
        sender.sendMessage("Joueur déjà retiré")
        return true
      }
    }
    sender.sendMessage("Whitelist:")
    whitelist.forEach(sender::sendMessage)
    return true
  }
}
