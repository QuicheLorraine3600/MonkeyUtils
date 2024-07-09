package com.monkeyteam.monkeyutils.modules

import com.monkeyteam.monkeyutils.MonkeyUtils
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object Home {
    object HomeCommand : CommandExecutor {
        private val config = MonkeyUtils.instance.config
        override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
            if (sender is Player) {
                val home = config.getLocation("homes." + sender.getName())
                if (home == null) {
                    sender.sendMessage("§rPas de home défini, faites §3/sethome §rpour en définir un !")
                    return true
                }
                sender.teleport(home)
            }
            return true
        }
    }

    object SetHomeCommand : CommandExecutor {
        private val config = MonkeyUtils.instance.config
        override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
            if (sender is Player) {
                config.set("homes." + sender.getName(), sender.location)
                MonkeyUtils.instance.saveConfig()
                sender.sendMessage("§3home §rdéfini, faites §3/home §rpour vous y téléporter")
            }
            return true
        }
    }
}