package com.monkeyteam.monkeyutils.modules

import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

object Back : Listener, CommandExecutor {
  private val deathPoints: MutableMap<Player, Location> = HashMap()

  @EventHandler
  fun onPlayerDeath(event: PlayerDeathEvent) {
    val player = event.entity
    deathPoints[player] = player.location
  }

  override fun onCommand(
      sender: CommandSender,
      cmd: Command,
      label: String,
      args: Array<out String>
  ): Boolean {
    if (sender is Player) {
      val deathLocation = deathPoints[sender]
      if (deathLocation != null) sender.teleport(deathLocation)
    }
    return true
  }
}
