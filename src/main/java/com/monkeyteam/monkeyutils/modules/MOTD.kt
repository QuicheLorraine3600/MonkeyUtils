package com.monkeyteam.monkeyutils.modules

import com.monkeyteam.monkeyutils.MonkeyUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

object MOTD : Listener {
  private val messages: List<String> = MonkeyUtils.instance.config.getStringList("motd")

  @EventHandler
  fun onPlayerJoin(event: PlayerJoinEvent) {
    val player = event.player
    messages.forEach(player::sendMessage)
  }
}
