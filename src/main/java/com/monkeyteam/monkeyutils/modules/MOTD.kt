package com.monkeyteam.monkeyutils.modules

import com.monkeyteam.monkeyutils.MonkeyUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object MOTD : Listener {
  @EventHandler
  fun onPlayerJoin(event: PlayerJoinEvent) {
    val messages: List<String> = MonkeyUtils.instance.config.getStringList("motd")
    val player = event.player
    messages.forEach(player::sendMessage)
    player.sendTitle("§eBANANAS", "")
  }
}
